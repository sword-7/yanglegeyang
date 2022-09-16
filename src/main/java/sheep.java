import okhttp3.HttpUrl;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import util.HttpClient;
import util.RandomUtil;

import java.io.IOException;

public class sheep {

	// 替换你的token
	private final static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTQyNzU0MjIsIm5iZiI6MTY2MzE3MzIyMiwiaWF0IjoxNjYzMTcxNDIyLCJqdGkiOiJDTTpjYXRfbWF0Y2g6bHQxMjM0NTYiLCJvcGVuX2lkIjoiIiwidWlkIjo1MjI5NTAyOSwiZGVidWciOiIiLCJsYW5nIjoiIn0.BlyAs5rMFb0kANZpGCZea0y9Zu91yWbATrlPpmKD45g";
	private final static Builder client = HttpClient.client;

	public static void main(String[] args) {
		System.out.println("【羊了个羊一键闯关开始启动】");
		int cycle_count = 10;
		for (int i=0; i<cycle_count; i++) {
			System.out.println("...开始完成第"+(i + 1)+"次闯关...");
			int cost_time = RandomUtil.getRandom(1, 3600);
			System.out.println("生成随机完成耗时:"+cost_time);
			if(finish_game(1, cost_time)){
				System.out.println("...完成第"+(i + 1)+"次闯关，闯关成功...");
				System.out.println("(【羊了个羊一键闯关结束】"+cost_time);
				break;
			}
			if(i==9){
				System.out.println("【羊了个羊闯关失败...】");
			}
			System.out.println("(【羊了个羊第"+(i + 1)+"次闯关失败，重试中...】"+cost_time);
		}
	}

	public static boolean finish_game(int state, int rank_time) {
		try {
			String finish_api = "https://cat-match.easygame2021.com/sheep/v1/game/game_over?rank_score=1&rank_state=%s&rank_time=%s&rank_role=1&skin=1";
			Request.Builder reqBuild = new Request.Builder();
			HttpUrl.Builder urlBuilder = HttpUrl.parse(String.format(finish_api, state, rank_time))
					.newBuilder();
			reqBuild.header("t", token);
			reqBuild.url(urlBuilder.build());
			Request request = reqBuild.build();

			Response response = null;
			try {
				response = client.build().newCall(request).execute();
				if (!response.isSuccessful()) {
					System.out.println("请求不成功===>"+response.toString());
				}
				System.out.println("执行成功===>"+response.body().string());
				return true;
			} catch (IOException e) {
				System.out.println("===>请求超时，服务器太多请求了，偶尔超时很正常");
				return false;
			} finally {
				if (response != null) {
					response.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
