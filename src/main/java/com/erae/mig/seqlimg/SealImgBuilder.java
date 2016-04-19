package com.erae.mig.seqlimg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.erae.mig.common.Log;
import com.erae.mig.common.MigConfig;
import com.erae.mig.common.SqlMapConfig;

public class SealImgBuilder {

	@SuppressWarnings("unchecked")
	public void migrationSealImg() {
		try {
			MigConfig migconfig = MigConfig.getInstance();
			List<Map<String, String>> sancList = (List<Map<String, String>>) SqlMapConfig.getSqlMapInstance().queryForList("getSeqlImgList");
			Log.log("[ list.size() : " + sancList.size() + "  ]", Log.INFO);
			
			for (Map<String, String> map : sancList) {
				fileCopy(map.get("old_path") + "", migconfig.getPropValues("repository") + File.separator + "images" , map.get("line_sign"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fileCopy(String inFileName, String outPath, String outFileName) {
		try {
			
			File file = new File(outPath);
			if (!file.exists()) {
		        file.mkdirs();
			}
			
			File f = new File(outPath + File.separator + outFileName);
			//f.createNewFile();
			
			FileInputStream fis = new FileInputStream(inFileName);
			FileOutputStream fos = new FileOutputStream(f);

			int data = 0;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			
			Log.log("[FR]" + inFileName + ", [TO]" + outFileName, Log.INFO);
			fis.close();
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
