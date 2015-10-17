package com.ardublock.translator.block.ontrack;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LCD_Backlight  extends TranslatorBlock {

	public LCD_Backlight (Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	//@Override
		public String toCode() throws SocketNullException, SubroutineNotDeclaredException
		{
			String line_number;
			TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
			line_number = translatorBlock.toCode();
			
			String isBlink;
			isBlink = this.getRequiredTranslatorBlockAtSocket(2).toCode();
			
			
			translator.addHeaderFile("Wire.h");
			translator.addHeaderFile("rgb_lcd.h");
			translator.addDefinitionCommand("rgb_lcd lcd;");
			//translator.addDefinitionCommand("//libraries at http://duinoedu.com/dl/lib/grove/EDU_LCD_RGB_Backlight_Grove/ \nrgb_lcd monRgb;");
			translator.addSetupCommand("lcd.begin(16, 2);");
			
			//translator.addSetupCommand("lcd.print(\"hello, world!\");");
			// Print a message to the LCD.
		    
				
			TranslatorBlock translatorBlock2 = this.getRequiredTranslatorBlockAtSocket(0);
			translator.addSetupCommand("lcd.print("+ translatorBlock2.toCode()+");");
			
			String ret = "";
			
			if(isBlink.equals("HIGH")) {
				 // Turn off the blinking cursor:
			    ret += "lcd.noBlink();";
			    ret +="delay(3000);";
			    // Turn on the blinking cursor:
			    ret += "lcd.blink();";
			    ret +="delay(3000);";
			}
			
			return ret ;	
		}
}
