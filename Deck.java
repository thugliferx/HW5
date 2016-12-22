package HW5;

import java.util.*;

public class Deck {
	public ArrayList<Card> usedCard = new ArrayList<Card>();
	public int nUsed;
	private ArrayList<Card> cards = new ArrayList<Card>();;
	private ArrayList<Card> openCard = new ArrayList<Card>(); 

	// TODO(finish): Please implement the constructor
	public Deck(int nDeck) {
		
		for (int i = 1; i <= nDeck; i++) {
			for (Card.Suit x : Card.Suit.values()) {
				for (int y = 1; y <= 13; y++) {
					Card card = new Card(x, y);
					cards.add(card);
				}
			}
		}
		// 1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		// Hint: Use new Card(x,y) and 3 for loops to add card into deck
		// Sample code start
		// Card card=new Card(1,1); ->means new card as clubs ace
		// cards.add(card);
		// Sample code end
		shuffle();
	}

	// TODO(finish): Please implement the method to print all cards on screen
	public void printDeck() {
		for (int index = 0; index < cards.size(); index++) {
			Card account = cards.get(index);
			account.printCard();
		}
		// Hint: print all items in ArrayList<Card> cards,
		// please implement and reuse printCard method in Card class

	}

	public ArrayList<Card> getAllCards() {
		return cards;
	}

	public void shuffle() {
		Random a = new Random();
		int p1, p2;
		for (int i = 1; i <= 52; i++) {// �~52���P
			p1 = a.nextInt(51);
			p2 = a.nextInt(50);
			Card scr = cards.get(p1);// �Ȧs��X���P
			cards.remove(p1);// �H����X�@�i�P,�åB�Ѿl���P�|�H���ɤW
			cards.add(p2, scr);// �q�ѤU��51�i�P����@�Ӧ�m�N��X���P��i�h
		}
		usedCard.clear();
		openCard.clear();
		nUsed = 0;
	}

	public Card getOneCard(boolean isOpened) {
		int n = usedCard.size();
		
			usedCard.add(cards.get(n));
			if(isOpened)openCard.add(cards.get(n));
			nUsed++;
			if (nUsed == 52)
			{
				Card a = usedCard.get(51);
				shuffle();
				return a;
			}
		
		return usedCard.get(n);
		
	}
	public ArrayList<Card> getOpenedCard() 
		{return openCard;}
}
/*
 * Description: TODO(finish): �B�z�C�ƵP�����B�I�ƪ�class�A�æ�printCard�BgetSuit
 * �BgetRank���T��instance��k�C
 */
