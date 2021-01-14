SUMMARY     = "HVAC application"
DESCRIPTION = "AGL demonstration HVAC application"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/hvac"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/hvac;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "${AGL_APP_REVISION}"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2 libhomescreen \
            qttools-native \
            libqtappfw"

RDEPENDS_${PN} = "agl-service-hvac libqtappfw"

inherit qmake5 aglwgt
