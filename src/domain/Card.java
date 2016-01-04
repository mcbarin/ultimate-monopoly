package domain;
/* status 401, player should choose from unpurchased stocks.
 * board'da getUnpurchasedStocks methodunu cagir. onlar company id'leri.
 * isimlerini çekerek listele. player seçince de applyCard47 methodunu çağır
 * applyCard47(Player p,int companyId)
 * */

/*
 * status 400, player square seçicek. şöyle ki,
 * result[0] 400'e eşit ise result[10] ve result[11] i parse ederek tut. onlar square idleri
 * o ikisi bir aralık belirtiyor(mesela result[10]=92, result[11]=106)
 * o aradaki tüm square idlerini oyuncuya listele. oyuncu seçince de 
 * applyCard0 methodunu çağır
 * applyCard0(int squareId,Player p)
 */

/*
 * her oyuncu sırası değiştiğinde player'ın countJail'i kontrol et.
 * eğer 10 ise player loses one turn. countJail'i de tekrar 4'e çevir.
 */

public class Card {
	private String type = "";
	public int number;
	public String title;

	// For chance card, "Chance"; for community chest card, "Community".

	public int getNumber() {
		return number;
	}

	public Card(String type, int number){
		this.type = type;
		this.number = number;		
	}
	
	public String getType() {
		return type;
	}


}
