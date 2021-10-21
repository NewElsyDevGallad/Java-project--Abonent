import java.io.*;

public class CompanyReport {

	// Byte method
	public void CreateAndSaveReport(String nameFile, Company company) {
		try {
			FileOutputStream fileOutput = new FileOutputStream(nameFile);
			String str = company.get_name();
			fileOutput.write(str.getBytes());
			fileOutput.write('\r');
			fileOutput.write('\n');
			fileOutput.write('\r');
			fileOutput.write('\n');
			String s = "  |  ";
			for (int i = 0; i < company.get_tarif_name().size(); i++) {
				Tarif t = (Tarif) company.get_tarif_name().get(i);
				String str1 = "�����: " + t.tarifName;
				fileOutput.write(str1.getBytes());
				fileOutput.write('\r');
				fileOutput.write('\n');
				for (int j = 0; j < t.get_abonents().size(); j++) {
					Abonent ab = (Abonent) t.get_abonents().get(j);
					str1 = ab.get_FN() + s + ab.get_number() + s + ab.get_account();
					fileOutput.write(str1.getBytes());
					fileOutput.write('\r');
					fileOutput.write('\n');
				}
				str1 = "-------------";
				fileOutput.write(str1.getBytes());
				fileOutput.write('\r');
				fileOutput.write('\n');
				str1 = "���-�� ��������� " + t.get_abonents().size();
				fileOutput.write(str1.getBytes());
				fileOutput.write('\r');
				fileOutput.write('\n');
			}
			fileOutput.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
	}

	public String UploadReport(String nameFile) {
		String s = null;
		try {
			FileInputStream fileInput = new FileInputStream(nameFile);// ��������� ���� �� ������
			int a = fileInput.available();// ���������� ���������� ���� � �������� �����
			byte[] b = new byte[a];// ��������� ������ ������ ���������������� �������
			fileInput.read(b);// ������ ���������� �����
			fileInput.close();// ��������� �����
			s = new String(b, "CP1251");// ������� ����� ������ �� ������ ���������� ������� ������

		} catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();
		}
		return s;
	}

	// Symbol method
	public void CreateAndSaveReport2(File nameFile, Company company) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(nameFile));/*OutputStreamWriter(new FileOutputStream(nameFile)*/
			String str = company.get_name();
			bw.write(str);
			bw.write('\r');
			bw.write('\n');
			bw.write('\r');
			bw.write('\n');
			String s = "  |  ";
			for (int i = 0; i < company.get_tarif_name().size(); i++) {
				Tarif t = (Tarif) company.get_tarif_name().get(i);
				String str1 = "�����: " + t.tarifName;
				bw.write(str1);
				bw.write('\r');
				bw.write('\n');
				for (int j = 0; j < t.get_abonents().size(); j++) {
					Abonent ab = (Abonent) t.get_abonents().get(j);
					str1 = ab.get_FN() + s + ab.get_number() + s + ab.get_account();
					bw.write(str1);
					bw.write('\r');
					bw.write('\n');
				}
				str1 = "-------------";
				bw.write(str1);
				bw.write('\r');
				bw.write('\n');
				str1 = "���-�� ��������� " + t.get_abonents().size();
				bw.write(str1);
				bw.write('\r');
				bw.write('\n');
			}
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
	}

	public String UploadReport2(String nameFile) {
		String str = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(nameFile));// ��������� ���� �� ������
			String str2;
			while ((str2 = br.readLine()) != null) {// ������ ���������� �����
				str += str2 + '\r' + '\n';
			}
			br.close();// ��������� �����
			
		}
		catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();
		}
		return str;
	}
}

