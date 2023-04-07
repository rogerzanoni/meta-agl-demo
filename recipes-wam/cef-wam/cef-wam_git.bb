# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   zanoni/temp/minimal_cmake_example/LICENSE
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3c40c681cf68ae88da55ad4cbc26ee1"

SRC_URI = "file:///home/zanoni/projects/github/cef-wam/ \
    file://WebAppMgr.service \
    file://WebAppMgr.env \
"

DEPENDS = "cef wayland wayland-native"
RDEPENDS:${PN} = "cef"

INSANE_SKIP:${PN} += "ldflags"

CEF_WAM_DATA_PATH = "${datadir}/cef-wam"

inherit cmake

S = "${WORKDIR}/home/zanoni/projects/github/cef-wam"

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Debug \
    -DCMAKE_INSTALL_PREFIX=${prefix} \
    -DCEF_ROOT='${STAGING_DATADIR}/cef' \
    -DCHROMIUM_SRC_DIR='${STAGING_DATADIR}/cef/include/chromium'"

PROVIDES += "virtual/webruntime"
RPROVIDES:${PN} += "virtual/webruntime"

do_install:append() {
    install -v -d ${D}${systemd_user_unitdir}
    install -v -m 644 ${WORKDIR}/WebAppMgr.service ${D}${systemd_user_unitdir}/WebAppMgr.service
    install -v -d ${D}${sysconfdir}/default/
    install -v -m 644 ${WORKDIR}/WebAppMgr.env ${D}${sysconfdir}/default/WebAppMgr.env
    install -v -d ${D}${systemd_user_unitdir}/agl-session.target.wants
    ln -sf ../WebAppMgr.service ${D}${systemd_user_unitdir}/agl-session.target.wants/

    install -v -d ${D}${CEF_WAM_DATA_PATH}
    cp -R --no-dereference --preserve=mode,links -v  ${STAGING_DATADIR}/cef/Release/* ${D}${CEF_WAM_DATA_PATH}
    cp -R --no-dereference --preserve=mode,links -v  ${STAGING_DATADIR}/cef/Resources/* ${D}${CEF_WAM_DATA_PATH}
}

INSANE_SKIP:${PN} += "already-stripped"
# TODO just for testing
do_package_qa[noexec] = "1"

FILES:${PN} += "${CEF_WAM_DATA_PATH} \
                ${sysconfdir} \
                ${systemd_user_unitdir} \
"
