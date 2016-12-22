package HW5;

import java.util.ArrayList;


public class Table {
	public static final int MAXPLAYER=4;//�̦h�@�i�P��৤�X�ӤH
	private Deck allCard;//�s��Ҧ����P
	private Player[]allPlayer;//�s��Ҧ������a
	private Dealer dealer;//�s��@�Ӳ��a
	private int[]pos_betArray;//�s��C�Ӫ��a�b�@���U���`
	public Table(int nDeck){//Constructor
	    allCard=new Deck(nDeck);
		allPlayer = new Player[MAXPLAYER];
		dealer=new Dealer();
	}
	
	public void set_player(int pos,Player p){//�NPlayer���P��W (�N�Y��� (d) �ܼƤ��A
//�� (d)�ܼƤ�setter)�Apos���P���m�A�̦h�|�H�Ap�h��Player instance�C
		allPlayer[pos]=p;
	}
	
	public Player[] get_player(){//�^�ǩҦ��b�P��W��player�A�N�Y�^�� (d)�ܼơA��(d)�ܼƤ�getter
		return allPlayer;
	}
	
	public void set_dealer(Dealer d){//�NDealer���P��W (�N�Y�NDealer��� (e) �ܼƤ��A
//�� (e)�ܼƤ�setter)�C
		dealer= d;
	}
	
	public Card get_face_up_card_of_dealer(){//�^��dealer���}�����i�P�A�]�N�O�ĤG�i�P
		ArrayList<Card> DealerCard=new ArrayList<Card>();
		DealerCard=dealer.getOneRoundCard();
		return DealerCard.get(1);
	}
	
	private void ask_each_player_about_bets(){//�ШC�Ӫ��a���۩I (���� say_hello())
		//�ШC�Ӫ��a�U�` (���� make_bet())
		//�C�Ӫ��a�U���`�n�s�bpos_betArray�Ѥ���ϥ�
		pos_betArray=new int[allPlayer.length];//allPlayer.length=4
		int i=0;
		while(i<allPlayer.length){
			if(allPlayer[i]!=null){
				allPlayer[i].say_hello();
				int bet=allPlayer[i].make_bet();
				if(bet>allPlayer[i].get_current_chips()){//�U�`����j���w�X
					pos_betArray[i]=0;//�Ϩ�U�`��0�Y�i
				}else{
					pos_betArray[i]=allPlayer[i].make_bet();
				}	
			}
			i++;
		}
		
	}
	
	private void distribute_cards_to_dealer_and_players(){//�o���P
		//Player�̪��P
		int i=0;
		while(i<allPlayer.length){
			if (allPlayer[i] != null && pos_betArray[i] != 0){
				 ArrayList<Card> playersCard = new ArrayList<Card>();
	                playersCard.add(allCard.getOneCard(true));
	                playersCard.add(allCard.getOneCard(true));
	                allPlayer[i].setOneRoundCard(playersCard);
			}
			i++;
		}	
		//Dealer���P
		ArrayList<Card> DealerCard = new ArrayList<Card>();
		DealerCard.add(allCard.getOneCard(false));//�\�۪�
		DealerCard.add(allCard.getOneCard(true));//���}��
		dealer.setOneRoundCard(DealerCard);
		System.out.print("Dealer's face up card is ");
		DealerCard.get(1).printCard();//���Dealer�}�۪��P
	}
	 
	private void ask_each_player_about_hits() {//�ݪ��a�O�_�n�P
	        int i = 0;
	        while (i < allPlayer.length) {
	            if (allPlayer[i] != null && pos_betArray[i] != 0) {
	                System.out.print(String.valueOf(allPlayer[i].get_name()) + "'s Cards now:");
	                allPlayer[i].printAllCard();
	                this.hit_process(i, allPlayer[i].getOneRoundCard());//�n�P�L�{�t�~�B�z
	                System.out.println(String.valueOf(allPlayer[i].get_name()) + "'s hit is over!");
	            }
	            ++i;
	        }
	    }

	private void hit_process(int pos, ArrayList<Card> cards) {//�n�P�L�{
	        boolean hit;
	        do {
	            if (hit = allPlayer[pos].hit_me(this)) {
	                cards.add(allCard.getOneCard(true));
	                allPlayer[pos].setOneRoundCard(cards);
	                System.out.print("Hit! ");
	                System.out.print(String.valueOf(allPlayer[pos].get_name()) + "'s Cards now:");
	                allPlayer[pos].printAllCard();
	                if (allPlayer[pos].getTotalValue() <= 21) continue;
	                hit = false;
	                continue;
	            }
	            System.out.println("Pass hit!");
	        } while (hit);
	    }
	
	private void ask_dealer_about_hits(){//�ݲ��a�O�_�n�P
		boolean hit;
		ArrayList<Card>cards=dealer.getOneRoundCard();
		do{
			if(hit=dealer.hit_me(this)){
				cards.add(allCard.getOneCard(true));
				dealer.setOneRoundCard(cards);
			}
			if(dealer.getTotalValue()<=21)continue;
			hit=false;
		}while(hit);
		System.out.println("Dealer's hit is over");
	}
	
	private void calculate_chips(){//�p���w�X
		int dealersCardValue=dealer.getTotalValue();
		System.out.print("Dealer's card value is "+dealersCardValue+",Card:");
		dealer.printAllCard();
		int i=0;
		while(i<allPlayer.length){
			if(allPlayer[i]!=null&&pos_betArray[i]!=0){
				System.out.print(String.valueOf(allPlayer[i].get_name())+"'s Cards:");
				allPlayer[i].printAllCard();
				caculate_process(i);//�p��L�{�t�~�B�z
			}
			i++;
		}
	}
	
	private void caculate_process(int pos){//�p��L�{
		 System.out.print(String.valueOf(allPlayer[pos].get_name()) + " card value is " + allPlayer[pos].getTotalValue());
	        if (allPlayer[pos].getTotalValue() > 21) {//���a�z��(1)
	            if (dealer.getTotalValue() > 21) {//���a�z���A�S��SĹ(1.1)
	                System.out.println(", chips have no change!, the Chips now is: " + allPlayer[pos].get_current_chips());
	            } else {//���a�S�z�A���a���(1.2)
	                allPlayer[pos].increase_chips(- pos_betArray[pos]);
	                System.out.println(", Loss " + pos_betArray[pos] + " Chips, the Chips now is: " + allPlayer[pos].get_current_chips());
	            }
	        } else if (allPlayer[pos].getTotalValue() == 21) {//���a=21�I(2)
	            if (allPlayer[pos].getOneRoundCard().size() == 2 && allPlayer[pos].hasAce()) {//Black jack!(2.1)
	                if (dealer.getTotalValue() != 21) {//���a�ä��O21�I�A���aĹ��(2.1.1)
	                    allPlayer[pos].increase_chips(this.pos_betArray[pos] );
	                    System.out.println(",Black jack!!! Get " + pos_betArray[pos] + " Chips, the Chips now is: " + allPlayer[pos].get_current_chips());
	                } else if (dealer.getOneRoundCard().size() == 2 && dealer.hasAce()) {//���a�]�OBlack jack!�A�S��SĹ(2.1.2)
	                    System.out.println(",Black Jack!!!! But chips have no change!, the Chips now is: " + allPlayer[pos].get_current_chips());
	                } else {//���a�O21�I�A�����OBlack Jack!���aĹ��(2.1.3)
	                    allPlayer[pos].increase_chips(pos_betArray[pos] );
	                    System.out.println(",Black jack!!! Get " + pos_betArray[pos] + " Chips, the Chips now is: " + allPlayer[pos].get_current_chips());
	                }
	                //���a21�I���èS��Black Jack!(2.2)
	            } else if (dealer.getTotalValue() != 21) {//���a�ä��O21�I�A���aĹ��(2.2.1)
	                allPlayer[pos].increase_chips(pos_betArray[pos] );
	                System.out.println(",Get " + pos_betArray[pos] + " Chips, the Chips now is: " + allPlayer[pos].get_current_chips());
	            } else {//���a�O21�I���a�O�]21�I�A�����S��Black Jack!�A�S��SĹ(2.2.2)
	                System.out.println(",chips have no change!The Chips now is: " + allPlayer[pos].get_current_chips());
	            }
	            //���a������21�I(3)
	        } else if (dealer.getTotalValue() > 21) {//���a�z���A���aĹ��(3.1)
	            allPlayer[pos].increase_chips(pos_betArray[pos]);
	            System.out.println(", Get " + pos_betArray[pos] + " Chips, the Chips now is: " + allPlayer[pos].get_current_chips());
	        } else if (dealer.getTotalValue() < allPlayer[pos].getTotalValue()) {//���a�I��<���a�I�ơA���aĹ��(3.2)
	            allPlayer[pos].increase_chips(pos_betArray[pos]);
	            System.out.println(", Get " + pos_betArray[pos] + " Chips, the Chips now is: " + allPlayer[pos].get_current_chips());
	        } else if (dealer.getTotalValue() > allPlayer[pos].getTotalValue()) {//���a�I��>���a�I�ơA���a���(3.3)
	            allPlayer[pos].increase_chips(- pos_betArray[pos]);
	            System.out.println(", Loss " + pos_betArray[pos] + " Chips, the Chips now is: " + allPlayer[pos].get_current_chips());
	        } else {//���a�I��=���a�I�ơA�S��SĹ(3.4)
	            System.out.println(", chips have no change! The Chips now is: " + allPlayer[pos].get_current_chips());
	        }
	    }
	
	public int[] get_palyers_bet(){
		return pos_betArray;
	}
	
	public void play(){//���@�p�����L�{
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}

	
	
}