package com.example;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Pinyin4jTeme {
	Logger logs = Logger.getLogger(this.getClass());

	@Before
	public void init() {
		logs.debug("初始化");
	}

	@After
	public void end() {
		logs.debug("测试结束");
	}

	@Test
	public void pinyin() {
		String chs = "海淀区";
		System.out.println("--->" + chs);
		System.out.println("====>" + getPinYin(chs));
	}

	public String getPinYin(String inputString) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE); // UPPERCASE 大写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // WITH_TONE_MARK
																// 音标
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		char[] input = inputString.trim().toCharArray();
		StringBuffer output = new StringBuffer("");
		try {
			for (int i = 0; i < input.length; i++) {
				boolean dq = Character.toString(input[i]).matches(
						"[\\u4e00-\\u9fa5]+");
				if (dq) {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(
							input[i], format);
					output.append(temp[0]);
					// output.append(" ");
				} else
					output.append(Character.toString(input[i]));
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}
