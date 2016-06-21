package com.erae.mig.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.erae.mig.common.MigConfig;

public class WFLineInfoSetUtil {

	MigConfig migconfig = MigConfig.getInstance();
    /*
     * 일반 결재 서명 이미지 config.getWOGWServer().trim()
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getLineData(List list) {
        boolean isPreApproval = false;
        String namePreApproval = "";
        String imgPreApproval = "";
        String datePreApproval = "";
        for (int i = 0; i < list.size(); i++) {
            Map data = (Map) list.get(i);
            if (data.get(IDataMapCode.SANC_YN).equals("1")) {
                if (data.get(IDataMapCode.SANC_PROGRESS).equals("002")) {
                    //전결인 경우
                    if (data.get(IDataMapCode.SANCTION_TYPE).equals("005") || data.get(IDataMapCode.SANCTION_TYPE).equals("105")) {
                        isPreApproval = true;
                        namePreApproval = data.get(IDataMapCode.SANC_LINE_USER_NAME) + "";
                        imgPreApproval = getApprovalImage(data);
                        datePreApproval = data.get(IDataMapCode.SANC_DATE) + "";
                        data.put(IDataMapCode.SANC_SIGN_IMAGE, getApprovalImageInstead());
                        data.put(IDataMapCode.SANC_DATE, getApprovalDateInstead());
                        // 결재안함 인 경우	
                    } else if (data.get(IDataMapCode.SANCTION_TYPE).equals("999")) {
                        data.put(IDataMapCode.SANC_SIGN_IMAGE, "<B><I>" + data.get("wowf_extend_name") + "</I></B>");
                         data.put(IDataMapCode.SANC_DATE, "&nbsp");
                    } else {
                        //  전결처리된 경우
                        if (isPreApproval
                                && (data.get(IDataMapCode.SANCTION_TYPE).equals("009") || data.get(IDataMapCode.SANCTION_TYPE).equals("109"))) {
                            data.put(IDataMapCode.SANC_SIGN_IMAGE, imgPreApproval);
                            data.put(IDataMapCode.SANC_DATE, datePreApproval);
                            // 대결인 경우	
                        } else if (data.get("wowf_instead_status") != null && data.get("wowf_instead_status").equals("2")) {
                            /* 2013.12.02 손탄 :: 중간 대결자의 사인이미지 가져오게 변경
                             * data.put(IDataMapCode.SANC_SIGN_IMAGE_HWP, "");
                             */
                            data.put(IDataMapCode.SANC_SIGN_IMAGE, "대결<br><B><I>" + data.get(IDataMapCode.SANC_LINE_USER_NAME) + "</I></B>");
                            // 결재 승인 했을때 기본 설정	
                        } else {
                            data.put(IDataMapCode.SANC_SIGN_IMAGE, getApprovalImage(data));
                        }
                    }
                    // 반려인 경우	
                } else if (data.get(IDataMapCode.SANC_PROGRESS).equals("003")) {
                    data.put(IDataMapCode.SANC_SIGN_IMAGE, "<div style='color:#FF0000;'><h4>반송</h4></div>");
                    // 중지인 경우
                } else if (data.get(IDataMapCode.SANC_PROGRESS).equals("005")) {
                    data.put(IDataMapCode.SANC_SIGN_IMAGE, "<div style='color:#FF0000;'><h4>중지</h4></div>");
                    // 진행상황인 경우	
                } else if (data.get(IDataMapCode.SANC_PROGRESS).equals("001")) {
                    // 전결 다음 최종결재는 001 로 결재가 승인 되므로 아래 로직을 추가
                    if (isPreApproval && (data.get(IDataMapCode.SANCTION_TYPE).equals("009") || data.get(IDataMapCode.SANCTION_TYPE).equals("109"))) {
                        data.put(IDataMapCode.SANC_SIGN_IMAGE, "<B><I>" + namePreApproval + "</I></B>");
                        data.put(IDataMapCode.SANC_DATE, datePreApproval);
                    }
                }
                // 보류인 경우	
            } else {

                if (data.get(IDataMapCode.SANC_PROGRESS).equals("004") || data.get(IDataMapCode.SANC_PROGRESS).equals("104")) {
                    data.put(IDataMapCode.SANC_SIGN_IMAGE, "<div style='color:#FF0000;'><h4>보류</h4></div>");
                } else if (isPreApproval
                        && (data.get(IDataMapCode.SANCTION_TYPE).equals("009") || data.get(IDataMapCode.SANCTION_TYPE).equals("109"))) {
                    data.put(IDataMapCode.SANC_SIGN_IMAGE, getApprovalImage(data));
                } else if (data.get(IDataMapCode.SANCTION_TYPE).equals("005") || data.get(IDataMapCode.SANCTION_TYPE).equals("105")) {
                    data.put(IDataMapCode.SANC_SIGN_IMAGE, "&nbsp");
                    data.put(IDataMapCode.SANC_SIGN_IMAGE, "&nbsp");
                    data.put(IDataMapCode.SANC_DATE, "&nbsp");
                } else {
                    data.put(IDataMapCode.SANC_SIGN_IMAGE, "&nbsp");
                    data.put(IDataMapCode.SANC_DATE, "&nbsp");
                }
            }
        }
        return list;
    }

    /*
     * 일반 결재 서명 이미지 config.getWOGWServer().trim()
     */
    @SuppressWarnings("rawtypes")
    public String getApprovalImage(Map data) {
    	if (!"none".equals(data.get(IDataMapCode.SANC_SIGN_IMAGE))) {
    		try {
    		    return "<img src=\"" + "file:///" + "/export/migration/images" + File.separator + data.get(IDataMapCode.SANC_SIGN_IMAGE) + "\">";
    		} catch(Exception e) {
    			return "<B><I>" + data.get(IDataMapCode.SANC_LINE_USER_NAME) + "</I></B>";
    		}
    	} else {
    		return "<B><I>" + data.get(IDataMapCode.SANC_LINE_USER_NAME) + "</I></B>";
    	}
    }

    /*
     * 일반 결재 결재자 이름(서명 이미지 대신 사용하는 경우)
     */
    @SuppressWarnings("rawtypes")
    public String getApprovalImageUserName(Map data, String compTable) {
//            if (!WFUtil.isNull(data.get(IDataMapCode.SANC_SIGN_IMAGE)) && data.get(IDataMapCode.SANC_SIGN_IMAGE).equals("-99")) {
//                if (!WFUtil.isNull(data.get("wowf_extend_name") + "")) {
//                    return "<B><I>" + data.get("wowf_extend_name") + "</I></B>";
//                } else {
//                    return "<B><I>부재중 승인</I></B>";
//                }
//            } else {
                return "<B><I>" + data.get(IDataMapCode.SANC_LINE_USER_NAME) + "</I></B>";
//            }
    }


    @SuppressWarnings("rawtypes")
    public String getApprovalImageInstead() {
        return"<B>전결</B>";
    }

    /*
     * 일반결재 전결 날짜
     */
    @SuppressWarnings("rawtypes")
    public String getApprovalDateInstead() {
        String result = "&nbsp";
        return result;
    }

    @SuppressWarnings("rawtypes")
    public String getApprovalNamet(Map data) {
        String result = "";

        return result;
    }
}
