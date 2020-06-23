public class MessageHandler {
    private static MessageHandler msgHandler;

    private MessageHandler(){}
    public static MessageHandler GetInstance(){
        if(msgHandler==null)
            msgHandler=new MessageHandler();
        return msgHandler;
    }
    public void HandleMessage(String msg){
        System.out.println(msg);
    }
}
