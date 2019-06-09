SUMMARY = "The software for AGL IVI DEMO profile"
DESCRIPTION = "A set of packages belong to AGL Demo"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-demo \
    "

ALLOW_EMPTY_${PN} = "1"

SMARTDEVICELINK = "${@bb.utils.contains('DISTRO_FEATURES', 'agl-sdl', \
    'packagegroup-agl-smartdevicelink', '', d)}"

# packages from hmi-framework aka homescreen-2017
HOMESCREEN = "packagegroup-hmi-framework"

# removed: now all enablers are in meta-agl-devel/meta-audio-soundmanager-framework
# old audio package
# AUDIO-OLD = "audiomanager"

RDEPENDS_${PN} += "\
    libqtappfw \
    ${HOMESCREEN} \
    udisks \
    ${SMARTDEVICELINK} \
    "

# fonts
TTF_FONTS = " \
    ttf-bitstream-vera \
    ttf-dejavu-sans \
    ttf-dejavu-sans-mono \
    ttf-dejavu-serif \
    source-han-sans-cn-fonts \
    source-han-sans-jp-fonts \
    source-han-sans-tw-fonts \
    "

#EXTRA_APPS_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'qtwebengine', '', d)}"
#EXTRA_APPS_append = " qtsmarthome cinematicexperience qt5everywheredemo qt5-demo-extrafiles"
#IMAGE_INSTALL_append = " qtwebengine-examples"

# add support for websocket in Qt and QML
EXTRA_APPS_append = " qtwebsockets qtwebsockets-qmlplugins"
PREFERRED_PROVIDER_virtual/webruntime = "web-runtime"


RDEPENDS_${PN} += " \
    linux-firmware-ath9k \
    can-utils \
    iproute2 \
    python-curses \
    dhcp-client \
    ${TTF_FONTS} \
    ${EXTRA_APPS} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webruntime', 'virtual/webruntime', '', d)} \
    "


