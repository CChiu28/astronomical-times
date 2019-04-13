public class Main {

	public static void main(String[] args) {
		gui.execute(args);
		String url = "brooklyn college";
		getData data = new getData();
		try {
			data.combo(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

