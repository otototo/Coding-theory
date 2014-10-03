import java.util.Vector;


/**
 * @author Paulina Morkyte. Kompiuteriu mokslas, 4kursas 
 * 
 * Uzduotis B2:
 * Kunas: q = p^m, p - pirminis, m>=1.
 * Egzistuoja abipusis rysys tarp ilgio n cikliniu kodu virs baigtinio kuno F_q ir polinomo x^n-1 virs F_q dalikliu. 
 * Todel, norint rasti visus ciklinius kodus, svarbu moketi faktorizuoti sita polinoma. 
 * Parasyti programa, kuri faktorizuoja x^n-1 virs Fq budu, aprasytu 
 * [VO89, §5.9, ypac 5.15 teorema (188 psl.) bei 28 pavyzdys (191–192 psl.)].
 */

public class MainB2 
{
	public static void main(String[] args) 
	{
		System.out.println("Programa pradeda darba.");
		int q, n;
		if (args.length != 2)
		{
			System.out.println("Paleidimui reikalingi paramterai: [q][n]");
		}
		else 
		{
			q = Integer.parseInt(args[0]);
			n = Integer.parseInt(args[1]);
		
			// pries vykdant faktorizacija patikrinti ar jie tarpusavyje pirminiai
			boolean pirminiai = tarpusavyPirminiai(q, n);
			if (pirminiai)
			{
				Faktorizacija f = new Faktorizacija(q);	
				Vector<Polinomas> p = f.faktorizuoti(q, n);
				for (Polinomas pol : p)
				{
					System.out.println(pol.toString());	
				}
			}
			else 
			{
				System.out.println("n ir q turi buti tarpusavyje pirminiai.");
			}

		}
		System.out.println("Programa baige darba.");
	}
	

	/**
	 * Skaiciuoja 2 naturaliuju skaiciu DBD.
	 * in: i1 ir i2 - sveiki skaiciai
	 * out: dbd - sveikas skaicius. 
	 * */
	public static int dbd(int i1, int i2)
	{
		if (i2 <= 1)
		{
			return i2;
		}
		else
		{
			int ret = dbd(i2, i1 % i2);
			return ret;
		}		
	}	
	
	/**
	 * Patikrina ar 2 naturalieji skaiciai yra tarpusavyje pirminiai.
	 * in: i1 ir i2 - naturalus skaiciai
	 * out: ar tarpusavyje pirminiai(true/false)
	 * */
	public static boolean tarpusavyPirminiai(int i1, int i2)
	{
		boolean pirminiai = false;
		int ret = dbd(i1, i2);
		if (ret == 1)
			pirminiai = true;
		return pirminiai;
	}
	
	
}
