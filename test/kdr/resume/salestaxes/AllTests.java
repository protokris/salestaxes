package kdr.resume.salestaxes;

import kdr.resume.salestaxes.item.DefaultItemTest;
import kdr.resume.salestaxes.item.factory.ItemFactoryTest;
import kdr.resume.salestaxes.money.MoneyTest;
import kdr.resume.salestaxes.receipt.DefaultReceiptTest;
import kdr.resume.salestaxes.store.StoreTest;
import kdr.resume.salestaxes.tax.TaxTest;
import kdr.resume.salestaxes.tax.list.DefaultTaxListTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The AllTests test suite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ InputTest.class, 
					  MoneyTest.class,
					  StoreTest.class, 
					  DefaultItemTest.class,
					  ItemFactoryTest.class,
					  DefaultReceiptTest.class,
					  TaxTest.class,
					  DefaultTaxListTest.class
					 })
public class AllTests {}