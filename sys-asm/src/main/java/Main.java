import com.baomidou.mybatisplus.extension.api.R;

/**
 * @author: 橘子
 * @date: 2022/6/30 11:19
 */
public class Main {

    public static void main(String[] args) {


    }

    private static int MESSAGE_KEY = 0x2019;
    @SuppressLint("HandlerLeak")
    private static Handler sHandler =new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what ==MESSAGE_KEY) {
                if (msg.obj!=null) {
                    Log.i("xmq", String.valueOf(msg.obj));
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendMessage(getClass().getSimpleName());
    }

    private void sendMessage(String string) {
        Message message = new Message();
        message.what =MESSAGE_KEY;
        sHandler.sendMessage(message);
    }
}
