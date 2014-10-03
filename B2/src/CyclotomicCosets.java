
import java.util.Vector;

/**Klase skirta cikliniu poaibiu suradimui.*/
public class CyclotomicCosets 
{
	private final static int NOT_FOUND = -1;
	
	private boolean [] used;
	private Vector<Vector<Integer>> cosets;
	
	public CyclotomicCosets() 
	{
		cosets = new Vector<Vector<Integer>>();
	}
	
	public Vector<Vector<Integer>> getCosets()
	{
		return cosets;
	}

	/** Pavercia rastus ciklinius poaibius i polinomu vektoriu.
	 * out: polinomu vektorius. */
	public Vector<Polinomas> getCosetsAsPolynoms()
	{
		Vector<Polinomas> poli = new Vector<Polinomas>();
		Polinomas temp;
		
		for (Vector<Integer> coset : cosets)
		{
			temp = new Polinomas();
			temp.initEmpty(used.length);
			for (Integer i : coset)
			{
				temp.setKoef(i, 1);				
			}
			poli.add(temp);
		}
		
		return poli;
	}
	/** Surandami cikliniai poaibiai. 
	 * Algoritmas paimtas is: http://cds.cern.ch/record/1026558?ln=en 
	 * in: n - auksciausias laipsnis,
	 *     q - kuno pagrindas.
	 * */
	public void findCosets(int n, int q)
	{
		cosets.removeAllElements();
		initIntegers(n);		
		Vector<Integer> temp;
		int number;
		
		for (int i = 0; (i < n) && !allFound(); i++)
		{
			temp = new Vector<Integer>();
			for (int j = 0; (j < n) && !allFound(); j++)
			{
				number = (int) ((i * Math.pow(q, j)) % n);
				if (wasUsed(number))
				{
					break;
				}
				else
				{
					setUsed(number);
					temp.add(number);
				}
			}
			if (temp.size() > 0)
				cosets.add(temp);
		}
		
	}

	/** Patikrinama ar visa aibe skaiciu jau isnaudota. 
	 * out: true/false
	 * */
	private boolean allFound() {
		return getNextUnused() == NOT_FOUND;
	}

	/** Pazymima kad skaicius jau panaudotas. 
	 * in: nr - panaudotas skaicius.
	 * */
	private void setUsed(int nr) {
		used[nr] = true;		
	}
	/** Paruosiama skaiciu aibe.
	 * Pazymima, kad ne vienas skaicius dar
	 * nepanaudotas.
	 * in: q - aibes dydis.*/
	private void initIntegers(int q)
	{
		used = new boolean[q];
		for (int i = 0; i < used.length; i++)
		{
			used[i] = false;
		}		
	}

	/** Patikrinima ar jau panaudotas.
	 * out: index - skaicius, kuri tikriname.*/
	private boolean wasUsed(int index)
	{
		return used[index];
	}

	/** Gaunamas kitas nepanaudotas skaicius.
	 * out: q - kitas skaicius.*/
	private int getNextUnused()
	{
		int ret = NOT_FOUND;
		for (int i = 0; i < used.length; i++)
		{
			if (!wasUsed(i))
			{
				ret = i;
				break;
			}
		}
		return ret;
	}
}
