SUMMARY = "The software for DEMO platform of AGL IVI profile"
DESCRIPTION = "A set of packages belong to AGL Demo Platform"

LICENSE = "MIT"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-agl-demo-platform-html5 \
    "

RDEPENDS:${PN} += "\
    packagegroup-agl-image-ivi \
    packagegroup-agl-profile-graphical-html5 \
    packagegroup-agl-demo \
    "

# TODO(jdapena): replace this with HTML5 apps.
AGL_APPS = ""

RDEPENDS:${PN}:append = " \
    weston-ini-conf-landscape-no-activate \
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'unzip' , '', d)} \
    qtquickcontrols2-agl \
    qtquickcontrols2-agl-style \
    ${AGL_APPS} \
    "

# nss-agl-driver-db is required to connect to kuksa
RDEPENDS:${PN}:append = " nss-agl-driver-db "

