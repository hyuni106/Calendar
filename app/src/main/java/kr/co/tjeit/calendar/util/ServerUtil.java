package kr.co.tjeit.calendar.util;

import android.content.Context;
import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KJ_Studio on 2015-12-24.
 */

public class ServerUtil {

    private static final String TAG = ServerUtil.class.getSimpleName();

//    private final static String BASE_URL = "http://13.125.83.235:8080/calendarApp/"; // 라이브서버
    private final static String BASE_URL = "http://192.168.100.110:8080/cal_app/"; // 개발서버

    //    JSON 처리 부분 인터페이스
    public interface JsonResponseHandler {
        void onResponse(JSONObject json);
    }

    //    이미지 업로드 기능
    public static void make_post(Context context, String user_id, String content, Bitmap bitmap, final JsonResponseHandler handler) {
        String url = BASE_URL + "insta/make_post";

        Map<String, String> data = new HashMap<String, String>();
        data.put("userId", user_id);
        data.put("content", content);


        AsyncHttpRequest.postWithImageFile(context, url, data, bitmap, "profile", new AsyncHttpRequest.HttpResponseHandler() {
            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
//
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }
        });
    }


    // 로그인
    public static void sign_in(final Context context, String user_id, String pw, final JsonResponseHandler handler) {
        String url = BASE_URL + "user/sign_in";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", user_id);
        data.put("password", pw);

        AsyncHttpRequest.post(context, url, data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 회원 가입
    public static void sign_up(final Context context, String user_id, String pw, String name, String nickname, String birth, final JsonResponseHandler handler) {
        String url = BASE_URL + "user/sign_up";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", user_id);
        data.put("password", pw);
        data.put("name", name);
        data.put("nickname", nickname);
        data.put("birth", birth);

        AsyncHttpRequest.post(context, url, data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 회원 가입시 아이디 중복 체크
    public static void check_dupl_id(final Context context, final String user_id, final JsonResponseHandler handler) {
        String url = BASE_URL + "user/check_dupl_id";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", user_id);

        AsyncHttpRequest.post(context, url, data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 모든 회원 불러오기
    public static void getAllUsers(final Context context, final JsonResponseHandler handler) {
        String url = BASE_URL + "group/allUsers";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();

        AsyncHttpRequest.post(context, url, data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 사용자의 모든 그룹 가져오기
    public static void participantGroup(final Context context, final int user_id, final JsonResponseHandler handler) {
        String url = BASE_URL + "user/userGroup";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", user_id + "");

        AsyncHttpRequest.post(context, url, data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 그룹 생성
    public static void createGroup(final Context context, final String name, String comment, String image_path, String user_id, String participant_user_id, final JsonResponseHandler handler) {
        String url = BASE_URL + "group/create";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("name", name);
        data.put("comment", comment);
        data.put("image_path", null);
        data.put("user_id", user_id);
        data.put("participant_user_id", participant_user_id);

        AsyncHttpRequest.post(context, url, data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 초대하기
    public static void inviteUser(final Context context, final String name, String comment, String image_path, final JsonResponseHandler handler) {
        String url = BASE_URL + "group/create";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("name", name);
        data.put("comment", comment);
        data.put("image_path", null);

        AsyncHttpRequest.post(context, url, data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 초대알림
    public static void getAllAlert(final Context context, int user_id, final JsonResponseHandler handler) {
        String url = BASE_URL + "user/get_alert";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", user_id + "");

        AsyncHttpRequest.post(context, url, data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 스케줄 생성
    public static void createSchedule(final Context context, String title, String memo, String start_date, String end_date, String location, int group_id, int user_id, int tag, final JsonResponseHandler handler) {
        String url = BASE_URL + "group/createSchedule";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("title", title);
        data.put("memo", memo);
        data.put("start_date", start_date);
        data.put("end_date", end_date);
        data.put("location", location);
        data.put("group_id", group_id + "");
        data.put("user_id", user_id + "");
        data.put("tag", tag + "");

        AsyncHttpRequest.post(context, url, data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 해당 그룹의 모든 스케줄 가져오기
    public static void getAllSchedule(final Context context, int group_id, final JsonResponseHandler handler) {
        String url = BASE_URL + "group/getAllSchedule";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("group_id", group_id + "");

        AsyncHttpRequest.post(context, url, data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 게시물 생성
    public static void createBoard(final Context context, String content, int group_id, int user_id, final JsonResponseHandler handler) {
        String url = BASE_URL + "group/createBoard";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("content", content);
        data.put("group_id", group_id + "");
        data.put("user_id", user_id + "");

        AsyncHttpRequest.post(context, url, data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 그룹 이름, 코멘트 변경
    public static void updateGroupInfo(final Context context, String name, String comment, int group_id, final JsonResponseHandler handler) {
        String url = BASE_URL + "group/updateGroupInfo";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("name", name);
        data.put("comment", comment);
        data.put("group_id", group_id + "");

        AsyncHttpRequest.post(context, url, data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 사용자 닉네임 변경
    public static void updateNickname(final Context context, String nickname, int user_id, final JsonResponseHandler handler) {
        String url = BASE_URL + "user/updateNickname";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("nickname", nickname);
        data.put("user_id", user_id + "");

        AsyncHttpRequest.post(context, url, data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 초대 수락 or 거절
    public static void updateParticipantStatus(final Context context, int status, int participant_id, int user_id, final JsonResponseHandler handler) {
        String url = BASE_URL + "user/participant_status";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("status", status + "");
        data.put("participant_id", participant_id + "");
        data.put("user_id", user_id + "");

        AsyncHttpRequest.post(context, url, data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

    // 초대 알림 수
    public static void getAlertCount(final Context context, int user_id, final JsonResponseHandler handler) {
        String url = BASE_URL + "user/alert_count";
        //		String registrationId = ContextUtil.getRegistrationId(context);

        Map<String, String> data = new HashMap<String, String>();
        data.put("user_id", user_id + "");

        AsyncHttpRequest.post(context, url, data, false, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }
}
