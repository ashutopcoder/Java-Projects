package com.game;

import java.util.ArrayList;
import java.util.Scanner;

public class TICTACTOE_GAME {
	public static void main(String args[]) {
		char a='1',b='2',c='3',d='4',e='5',f='6',g='7',h='8',i='9';
		char player='X';
		ArrayList<Integer> al=new ArrayList<>();
		Scanner sc=new Scanner(System.in);
		while(true) {
		System.out.println("****TIC-TAC-TOE****");
		System.out.printf("\n%c|%c|%c\n",a,b,c);
		System.out.println("------");
		System.out.printf("%c|%c|%c\n",d,e,f);
		System.out.println("------");
		System.out.printf("%c|%c|%c\n",g,h,i);
		System.out.printf("\nEnter Value For Player %c : ",player);
		int val=sc.nextInt();
		if((!al.contains(val))&&(val>0)&&(val<10)) {
			al.add(val);
			switch(val) {
			case 1:
				a=player;
				break;
			case 2: 
				b=player;
				break;
			case 3:
				c=player;
				break;
			case 4: 
				d=player;
				break;
			case 5:
				e=player;
				break;
			case 6:
				f=player;
				break;
			case 7:
				g=player;
				break;
			case 8:
				h=player;
				break;
			case 9:
				i=player;
				break;
			}
			if((a==b&&b==c)||(d==e&&e==f)||(g==h&&h==i)||(a==d&&d==g)||(b==e&&e==h)||(c==f&&f==i)||(a==e&&e==i)||(c==f&&f==g)) {
				System.out.printf("\n\n%c PLAYER WIN\nCONGRULATIONS..!\n",player);
				System.out.printf("%c|%c|%c\n",a,b,c);
				System.out.println("------");
				System.out.printf("%c|%c|%c\n",d,e,f);
				System.out.println("------");
				System.out.printf("%c|%c|%c\n",g,h,i);
				break;
			}
			if(player=='X')
				player='0';
			else
				player='X';
		}
		else {
			System.out.println("Box Always Used,Select Other One.");
		}
		if(al.size()==9) {
			System.out.println("No One Is Winning ,Please Restart .");
		}
		}
	}
}
