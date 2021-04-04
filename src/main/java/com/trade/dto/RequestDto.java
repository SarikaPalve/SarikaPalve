package com.trade.dto;

import java.util.Date;

public class RequestDto {
	
	  private String trade_id;
	    private Integer version;
	    private String counter_party_id;
	    private String book_id;
	    private Date maturity_date;
		public String getTrade_id() {
			return trade_id;
		}
		public void setTrade_id(String trade_id) {
			this.trade_id = trade_id;
		}
		
		public Integer getVersion() {
			return version;
		}
		public void setVersion(Integer version) {
			this.version = version;
		}

		
		public String getCounter_party_id() {
			return counter_party_id;
		}
		public void setCounter_party_id(String counter_party_id) {
			this.counter_party_id = counter_party_id;
		}
		public String getBook_id() {
			return book_id;
		}
		public void setBook_id(String book_id) {
			this.book_id = book_id;
		}
		public Date getMaturity_date() {
			return maturity_date;
		}
		public void setMaturity_date(Date maturity_date) {
			this.maturity_date = maturity_date;
		}
		

}
