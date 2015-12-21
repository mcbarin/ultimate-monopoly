
public class Company {

	String name;
	int id;
	int parValue; 
	int share = 0; //each company has 5 share. 0 share sold at first.
	
	public Company(String name, int id, int parValue) {
		this.name=name;
		this.id=id;
		this.parValue=parValue;
	}

	
/*	Par Value	$ 100	$ 110	$ 120	$ 130	$ 140	$ 150
	1 Share		$ 10	$ 11	$ 12	$ 13	$ 14	$ 15
	2 Shares	$ 40	$ 44	$ 48	$ 52	$ 56	$ 60
	3 Shares	$ 90	$ 99	$ 108	$ 117	$ 126	$ 135
	4 Shares	$ 160	$ 176	$ 192	$ 208	$ 224	$ 240
	5 Shares	$ 250	$ 275	$ 300	$ 325	$ 350	$ 375  */
	
	public int getDividend(int share){
			return (10+id) * share^2;
			
	}
	
	
}
