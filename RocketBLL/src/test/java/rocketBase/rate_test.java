package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class rate_test {

	// TODO - RocketBLL rate_test
	// Check to see if a known credit score returns a known interest rate

	// TODO - RocketBLL rate_test
	// Check to see if a RateException is thrown if there are no rates for a
	// given
	// credit score
	
	@Test (expected = RateException.class)

	public void test() throws RateException {
		int rate1 = 555;
		double r= RateBLL.getRate(rate1);
		
		assertEquals(5.5,r,0.01);
	}
	@Test 

	public void test_2() throws RateException {
		int rate1 = 601;
		double r= RateBLL.getRate(rate1);
		
		assertEquals(7,r,0.01);
	}
	
	@Test
	public void test3() throws RateException{
		
		ArrayList<RateDomainModel> alrates;
		alrates = RateDAL.getAllRates();
		for (RateDomainModel r:alrates){
			double rate = RateBLL.getRate(r.getiMinCreditScore());
			assertEquals(r.getdInterestRate(),rate,0.01);
		}
		
	}
	@Test
	public void test_payment(){
		double pv =300000;
		double fv=0;
		double numpays = 360;
		double rate=0.04/12;
		
		double payment= RateBLL.getPayment(rate, numpays, pv, fv, false);
		assertEquals(-1432.25,payment,0.01);
	}
	
}
