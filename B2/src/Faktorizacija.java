
import java.util.Vector;

/** Atlieka polinomu faktorizacija. */
public class Faktorizacija 
{	
	private CyclotomicCosets cosets;
	private PrimityvusPolinomai primityvusPolinomai;	

	/** 
	 * Klases konstruktorius. 
	 * Suranda kuno ciklinius paibius, kuno pagrinda, 
	 * kūno elementu skaiciu.
	 * in: q - baigtinio kūno pagrindas
	 * */
	public Faktorizacija(int q) 
	{
		cosets = new CyclotomicCosets();
		rastiPirmini(q);
		primityvusPolinomai = new PrimityvusPolinomai();
		
		/** Pagal gauta kūno pagrindo pirmini skaiciu ir jo laipsni,
		 * surandame kūno elementu skaiciavimams naudojama primityvu
		 * polinoma.*/
		KunoElementas.setPrimitive(primityvusPolinomai.getPrimitive(
				KunoElementas.getP(), KunoElementas.getM()));	
	}

	/** Pradedama faktorizacija.
	 * in: q - kūno pagrindas,
	 * 	   n - polinomo laipsnis.
	 * out: polinomo skaidinys. */
	public Vector<Polinomas> faktorizuoti(int q, int n) 
	{
		//suluriame pradini fx
		Polinomas fx = new Polinomas();
		fx.addKoef(-1);
		for (int i = 1; i < n; i++)
			fx.addKoef(0);
		fx.addKoef(1);
		
		// randame ciklinius poabius.
		cosets.findCosets(n, q);
		Vector<Polinomas> Ci = cosets.getCosetsAsPolynoms();
		Ci = pasalintiTrivialius(Ci, fx);
		
		Vector<Polinomas> neredukuojamos = new Vector<Polinomas>();		
		neredukuojamos = redukuoti(fx, Ci, 0);
		
		return neredukuojamos;
	}
		
	/** Atliekama polinomo redukcija.
	 * Formules [VO89], §5.9, 5.15 teoremos (188 psl.) igyvendinimas.
	 * in: fx - polinomas, kuri reikes skaidyti,
	 *     ci - cikliniai poaibiai,
	 *     i - ciklinio paibio indeksas
	 * out: polinomo skaidinys.
	 * */
	private Vector <Polinomas> redukuoti(Polinomas fx, Vector<Polinomas> ci, int i) {
		Vector<Polinomas> ats = new Vector<Polinomas>();
		Vector<Polinomas> atsRed = new Vector<Polinomas>();
		Polinomas atsDbd = new Polinomas();
		
		if (i < ci.size())
		{
			KunoElementas q = new KunoElementas(0);
			for (int qi = 0; qi < KunoElementas.getQ(); qi++)
			{
				atsDbd = dbd(fx, ci.get(i).atimtis(q));
				atsRed = redukuoti(atsDbd, ci, i+1);
				for (Polinomas p : atsRed)
				{
					if (!yraIdetas(ats, p))
					{
						ats.add(p);
					}
				}
				q.increment();
			}
		}
		else
		{
			if (!yraNulinioLaipsnio(fx))
			{
				if (!yraIdetas(ats, fx))
					ats.add(fx);
			}
		}
		return ats;
	}
	
	/** Tikrinama ar vektoriuje ats jau egzistuoja polinomas fx. 
	 * in: ats - polinomu vektorius,
	 *     fx - ieskomas polinomas
	 * out: ar polinomas rastas vektoriuje (true/false).
	 * */
	private boolean yraIdetas(Vector<Polinomas> ats, Polinomas fx) {
		boolean contains = false;
		for (Polinomas p : ats)
		{
			if (p.equals(fx))
			{
				contains = true;
				break;
			}
		}
		return contains;
	}
	
	/** Pasalinami trivialūs (vienetiniai 
	 * ir sutampantys su pradiniu polinomu) polinomai.
	 * in: ci - polinomu vektorius,
	 *     fx - pradinis polinomas,
	 * out: polinomu vektorius is kurio jau 
	 * pasalinti trivialūs polinomai.
	 * */
	private Vector<Polinomas> pasalintiTrivialius(Vector<Polinomas> ci, Polinomas fx) {
		Vector<Polinomas> temp = new Vector<Polinomas>();
		for (Polinomas c : ci)
		{
			if (c.equals(fx) || yraVienetinis(c) || yraNulinioLaipsnio(c))
			{				
			}
			else
			{
				temp.add(c);
			}
		}
		return temp;		
	}
	
	/** Ar polinomas turi tik koeficienta prie nulinio laipsnio.
	 * in: fx - polinomas,
	 * out: true/false
	 * */
	private boolean yraNulinioLaipsnio(Polinomas fx)
	{
		return fx.getDegree() <= 0;
	}
	
	/** Ar polinomas lygus 1.
	 * in: fx - polinomas,
	 * out: true/false
	 * */
	private boolean yraVienetinis(Polinomas fx)
	{
		Polinomas vienetinis = new Polinomas(1);
		return fx.equals(vienetinis);
	}

	/** Skaiciuoja polinomu dbd.
	 * Algoritmas paremtas 
	 * http://en.wikipedia.org/wiki/Polynomial_greatest_common_divisor#Arithmetic_of_algebraic_extensions
	 * in: polinomas1, polinomas2 - polinomai, kuriu dbd reikia surasti
	 * out: dbd - rastas polinomū dbd, kuris yra taip pat polinomas.
	 * */
	public Polinomas dbd(Polinomas polinomas1, Polinomas polinomas2)
	{
		if (yraVienetinis(polinomas1))
			return polinomas1;
		if (yraVienetinis(polinomas2))
			return polinomas2;
		if (polinomas2.yraNulis())
		{
			return polinomas1;
		}
		else
		{
			Polinomas ret = dbd(polinomas2, polinomas1.dalyba(polinomas2).get(1));
			return ret;
		}		
	}	


	/** Ieskomas tokie p ir m, kad p^m = q.
	 * in: q - Kūno pagrindas
	 * out: ar pavyko surasti (true/false)
	 * */
	public boolean rastiPirmini(int q) 
	{
		boolean ret = false;
		int primite_base[] = {2, 3, 5, 7, 11, 13, 17, 19, 23};
		for (int base : primite_base) {
			for (int i = 1; i < 10; i++) {
				if (Math.pow(base, i) == q) 
				{
					setP(base);
					setM(i);
					ret = true;
					break;
				}
			}
		}
		setQ(q);	
		return ret;
	}	

	private void setP(int p) 
	{
		KunoElementas.setP(p);		
	}
	
	private void setM(int m) 
	{
		KunoElementas.setM(m);		
	}
	
	private void setQ(int q) 
	{
		KunoElementas.setQ(q);		
	}
}
