package HW5;
public class Player extends Person {
	private String name;//���a�m�W
	private int chips; //���a�����w�X
	private int bet=0;//���a�����U�`���w�X
	public Player(String name, int chips){//Player constructor
		this.name=name;
		this.chips=chips;
	}
	public String get_name(){//name��getter 
		return name;
	}
	public int make_bet(){//�U�`�A�^�ǹw�p�U�`���w�X
		bet=100;
		if(chips<100)
			{bet=10;}
		if(chips<10)
			{bet=1;}
		if(chips<1)
			{bet=0;}
		return bet;
	}
	public boolean hit_me(Table table){//�O�_�n�P�A�O�^��true�A���A�n�P�h�^��false 
		int value=getTotalValue();
		if(value<=16){return true;}//�C��16�I�n�P
		return false;
		
	}
	public int get_current_chips(){//�^�Ǫ��a�{���w�X
		return chips;
	} 
	public void increase_chips (int diff) {//���a�w�X�ܰʪ�setter
		chips+=diff;
	}
	public void say_hello(){//���aSay Hello 
		System.out.println("Hello, I am " + name + "."); 
		System.out.println("I have " + chips + " chips.");
	}
	
	
	
}
