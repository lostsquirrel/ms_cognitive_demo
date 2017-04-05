package demo.cognitive;

import demo.cognitive.utils.ConfigUtils;
import demo.cognitive.utils.EmotionUtils;

public class HelloEmotion {

	
	public HelloEmotion() {
		String url = ConfigUtils.get(ConfigUtils.CS_API_URL);
		String key = ConfigUtils.get(ConfigUtils.CS_API_KEY);
		EmotionUtils.init(key, url);
	}

	public static void main(String[] args) {
		String image = "http://b89.photo.store.qq.com/psb?/40855d2c-b18f-4345-a5c4-e91215af837b/kvLCbK0CMMvqga55Xxg3gQfXE**n9s.50O*o2equ28o!/b/dA8pIDXVVQAA&bo=VQIgAwAAAAABB1Q!&rf=viewer_4";
		System.out.println(EmotionUtils.cog(image));
	}

	
}
