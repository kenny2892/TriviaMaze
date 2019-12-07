package application.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.KeyBindings;
import javafx.scene.input.KeyCode;

class KeyBindingsTests
{

	@Test
	void getNorth_defaultValueIsKeyCodeUP_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		assertTrue(sut.getNorth() == KeyCode.UP);
	}
	
	@Test
	void setNorth_valueIsOfSetTypeEQUALS_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		sut.setNorth(KeyCode.EQUALS);
		assertTrue(sut.getNorth() == KeyCode.EQUALS);
	}
	
	@Test
	void getSouth_defaultValueIsKeyCodeDOWN_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		assertTrue(sut.getSouth() == KeyCode.DOWN);
	}
	
	@Test
	void setSouth_valueIsOfSetTypeEQUALS_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		sut.setSouth(KeyCode.EQUALS);
		assertTrue(sut.getSouth() == KeyCode.EQUALS);
	}
	
	@Test
	void getEast_defaultValueIsKeyCodeRIGHT_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		assertTrue(sut.getEast() == KeyCode.RIGHT);
	}
	
	@Test
	void setEast_valueIsOfSetTypeEQUALS_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		sut.setEast(KeyCode.EQUALS);
		assertTrue(sut.getEast() == KeyCode.EQUALS);
	}
	
	@Test
	void getWest_defaultValueIsKeyCodeLEFT_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		assertTrue(sut.getWest() == KeyCode.LEFT);
	}
	
	@Test
	void setWest_valueIsOfSetTypeEQUALS_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		sut.setWest(KeyCode.EQUALS);
		assertTrue(sut.getWest() == KeyCode.EQUALS);
	}
	
	@Test
	void getCustomize_defaultValueIsKeyCodeQ_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		assertTrue(sut.getCustomize() == KeyCode.Q);
	}
	
	@Test
	void setCustomize_valueIsOfSetTypeEQUALS_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		sut.setCustomize(KeyCode.EQUALS);
		assertTrue(sut.getCustomize() == KeyCode.EQUALS);
	}
	
	@Test
	void getSave_defaultValueIsKeyCodeW_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		assertTrue(sut.getSave() == KeyCode.W);
	}
	
	@Test
	void setSave_valueIsOfSetTypeEQUALS_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		sut.setSave(KeyCode.EQUALS);
		assertTrue(sut.getSave() == KeyCode.EQUALS);
	}
	
	@Test
	void getLoad_defaultValueIsKeyCodeE_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		assertTrue(sut.getLoad() == KeyCode.E);
	}
	
	@Test
	void setLoad_valueIsOfSetTypeEQUALS_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		sut.setLoad(KeyCode.EQUALS);
		assertTrue(sut.getLoad() == KeyCode.EQUALS);
	}
	
	@Test
	void getSettings_defaultValueIsKeyCodeR_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		assertTrue(sut.getSettings() == KeyCode.R);
	}
	
	@Test
	void setSettings_valueIsOfSetTypeEQUALS_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		sut.setSettings(KeyCode.EQUALS);
		assertTrue(sut.getSettings() == KeyCode.EQUALS);
	}
	
	@Test
	void getHelp_defaultValueIsKeyCodeT_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		assertTrue(sut.getHelp() == KeyCode.T);
	}
	
	@Test
	void setHelp_valueIsOfSetTypeEQUALS_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		sut.setHelp(KeyCode.EQUALS);
		assertTrue(sut.getHelp() == KeyCode.EQUALS);
	}
	
	@Test
	void getCheatIdentifier_returnsCorrectKeyCode_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		assertTrue(sut.getCheatIdentifier() == KeyCode.SLASH);
	}
	
	@Test
	void usesKey_returnsTrueifKeyCodeUPProvided_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		assertTrue(sut.usesKey(KeyCode.UP));
	}
	
	@Test
	void usesKey_returnsTrueifKeyCodeNORTHProvided_TRUE()
	{
		KeyBindings sut = new KeyBindings();
		assertTrue(sut.usesKey(KeyCode.DOWN));
	}

}
