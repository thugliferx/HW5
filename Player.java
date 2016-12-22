package HW5;
public class Player extends Person {
	private String name;//玩家姓名
	private int chips; //玩家有的籌碼
	private int bet=0;//玩家此局下注的籌碼
	public Player(String name, int chips){//Player constructor
		this.name=name;
		this.chips=chips;
	}
	public String get_name(){//name的getter 
		return name;
	}
	public int make_bet(){//下注，回傳預計下注的籌碼
		bet=100;
		if(chips<100)
			{bet=10;}
		if(chips<10)
			{bet=1;}
		if(chips<1)
			{bet=0;}
		return bet;
	}
	public boolean hit_me(Table table){//是否要牌，是回傳true，不再要牌則回傳false 
		int value=getTotalValue();
		if(value<=16){return true;}//低於16點要牌
		return false;
		
	}
	public int get_current_chips(){//回傳玩家現有籌碼
		return chips;
	} 
	public void increase_chips (int diff) {//玩家籌碼變動的setter
		chips+=diff;
	}
	public void say_hello(){//玩家Say Hello 
		System.out.println("Hello, I am " + name + "."); 
		System.out.println("I have " + chips + " chips.");
	}
	
	
	
}
