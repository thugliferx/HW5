package HW5;

public class Card {
	public enum Suit{Club,Diamond,Heart,Spade};
	private Suit suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; //1~13
	public Card(Suit s, int value){
		suit=s;
		rank=value;
	}	
	//TODO(finish): 1. Please implement the printCard method
	public void printCard(){
		System.out.println(getSuit()+" "+getRank());
			
		
		//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace

	}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}

	}

 