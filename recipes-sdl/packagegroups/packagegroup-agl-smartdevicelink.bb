DESCRIPTION = "Set of packages for SmartDeviceLink"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-smartdevicelink \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    sdl-core \
    "
