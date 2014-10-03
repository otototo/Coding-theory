import java.util.Vector;

/** Apibrezia kuno elementus ir ju veiksmus kune su pagrindu q. */

public class KunoElementas
{
	private static KunoElementas primityvusPolinomas;
	private static int p;
	private static int m;
	private static int q;
	private Vector<Integer> elementKoef;
	
	
	/** Sukuriamas kuno elemtas su duotais koeficientais.*/
	public KunoElementas(int... elementoKoef) 
	{	
		elementKoef = new Vector<Integer>();

		/** Gali tekti inicijuoti didesniam koeficientu skaiciui, kadangi
		 * kaip KunoElementai yra apibreziami ir PrimityvusPolinomai.*/
		elementKoef = initEmptyVector(Math.max(elementoKoef.length, getM()));
		for (int i = 0; i < elementoKoef.length; i++)
		{
			if (elementoKoef[i] < 0)
				elementKoef.set(i, elementoKoef[i] + getP());
			else
				elementKoef.set(i, elementoKoef[i]);
		}
	}
	
	/**
	 * @return pirminio skaiciaus laipsnis
	 */
	public static int getP()
	{
		return KunoElementas.p;
	}
	/**
	 * @return pirminis skaicius
	 */
	public static int getM()
	{
		return KunoElementas.m;
	}
	/**
	 * @return Baigtinio kuno elementu skaicius*
	 */
	public static int getQ() 
	{
		return KunoElementas.q;
	}/**
	 * pirminio skaiciaus laipsnis
	 */
	public static void setP(int p) 
	{
		KunoElementas.p = p;
	}
	public static void setQ(int q) 
	{
		KunoElementas.q = q;
	}
	/**
	 * pirminis skaicius
	 */
	public static void setM(int m)
	{
		KunoElementas.m = m;
	}
	/**
	 * @return gauti primityvu polinoma*
	 */
	public static KunoElementas getPrimitive() 
	{
		return primityvusPolinomas;
	}
	/**
	 * @return irasyti primityvu polinoma*
	 */
	public static void setPrimitive(KunoElementas kunoElementas)
	{
		primityvusPolinomas = kunoElementas;
	}
	
	/**
	 * @return the element as a Vector&ltInteger&gt
	 */
	public Vector<Integer> getElementAsVector() 
	{
		return elementKoef;
	}
	
	/** Inicijuojamas vektorius dydziu size.
	 * Visi jo elementai perrasomi 0.*/
	private Vector<Integer> initEmptyVector(int size)
	{
		Vector<Integer> temp = new Vector<Integer>();
		for (int i = 0; i < size; i++)
			temp.add(0);
		return temp;
	}	

	/** Sveikuju skaiciu a ir b sandauga moduliu p.*/
	private int sandaugaModuliu(int a, int b) 
	{
		int rez = a * b;
		rez = Math.abs(rez % getP());
		return rez;
	}

	/** Ar turimas kuno elementas nelygus duotam skaiciui i.
	 * Skaicius i konvertuojamas i Kuno elementa.*/
	public boolean nelygu(int i) 
	{
		return nelygu(new KunoElementas(i));
	}

	/** Ar turimas kuno elementas nelygus duotam KunoElementui elem.*/
	public boolean nelygu(KunoElementas elem)
	{
		return !lygu(elem);
	}

	/** Ar turimas kuno elementas mazesnis uz duota KunoElementa elem.*/
	public boolean maziauUz(KunoElementas elem) 
	{
		boolean ret = false;
		
		if (this.getDegree() < elem.getDegree())
			ret = true;
		else if (this.getDegree() == elem.getDegree())
		{
			for (int i = getSize() - 1; i >= 0; i--)
			{
				if (this.getElement(i) < (elem.getElement(i)))
				{
					ret = true;
					break;
				}
				else if (this.getElement(i) > (elem.getElement(i)))
				{
					break;
				}
			}
		}		
		return ret;
	}

	/** Ar turimas kuno elementas didesnis uz duota KunoElementa elem.*/
	public boolean daugiauUz(KunoElementas elem) 
	{
		boolean ret = false;
		
		if (this.getDegree() > elem.getDegree())
			ret = true;
		else if (this.getDegree() == elem.getDegree())
		{
			for (int i = getSize() - 1; i >= 0; i--)
			{
				if (this.getElement(i) > (elem.getElement(i)))
				{
					ret = true;
					break;
				}
				else if (this.getElement(i) < (elem.getElement(i)))
				{
					break;
				}
			}
		}		
		return ret;
	}	

	/** Ar turimas kuno elementas lygus duotam skaiciui i.
	 * Skaicius i pasiverciamas i kuno elementa.*/
	public boolean lygu(int i) 
	{
		return lygu(new KunoElementas(i));
	}

	/** Ar turimas kuno elementas lygus duotam KunoElementui elem.*/
	public boolean lygu(KunoElementas elem) 
	{
		boolean ret = false;
		
		if (this.getDegree() == elem.getDegree())
		{
			for (int i = getSize() - 1; i >= 0; i--)
			{
				if (this.getElement(i) == (elem.getElement(i)))
				{
					ret = true;
					break;
				}
			}
		}		
		return ret;
	}

	/** Atimtis dvieju skaiciu value1 ir value2 moduliu p*/
	public int atimtisModuliu(int value1, int value2) 
	{
		value1 = value1 - value2;
		while (value1 < 0) value1 += getP();
		return value1;
	}

	/** Is turimo kuno elemento atimamas duotas kuno elementas elem.
	 * */
	public KunoElementas atimtis(KunoElementas elem)
	{
		KunoElementas rezultatas = new KunoElementas();
		int max = Math.max(this.getSize(), elem.getSize());
		int rez;
		for (int i = 0; i < max; i++) 
		{
			rez = atimtisModuliu(this.getElement(i), (elem.getElement(i))); 
			rezultatas.setElement(i, rez);
		}
		return rezultatas;
	}

	/** Prie turimo kuno elemento pridedamas duotas kuno elementas elem.
	 * */
	public KunoElementas suma(KunoElementas elem) 
	{				
		int max = Math.max(this.getSize(), elem.getSize());	
		int rez;
		KunoElementas rezultatas = new KunoElementas();
		for (int i = 0; i < max; i++) 
		{
			rez = sumaModuliu(this.getElement(i), elem.getElement(i));
			rezultatas.setElement(i, rez);
		}
		return rezultatas;
	}	

	/** Sudedami du skaiciai a ir b moduliu p.
	 * */
	private int sumaModuliu(int a, int b) 
	{
		int rez = a + b;
		rez = rez % getP();
		return rez;
	}

	/** Turimas kuno elementas padauginamas is skaiciaus pow.
	 * Skaicius pow paverciamas i kuno elementa.*/
	public KunoElementas sandauga(int pow) 
	{
		return sandauga(new KunoElementas(pow));
	}

	/**
	 * Is turimo kuno elemento padauginamas perduotas kuno elementas kunoElem.
	 * Gautas sandaugos rezultatas dalinamas moduliu is primityvaus polinomo.
	 * Grazinama dalybos liekana.
	 * */
	public KunoElementas sandauga(KunoElementas kunoElem) 
	{		
		KunoElementas rezultatas = new KunoElementas(0); 
		int dydis = this.getSize()+kunoElem.getSize();
		rezultatas.setElement(dydis, 0);
		KunoElementas tempRezultatas = null;		
		int pk1, pk2, rezSandaugos;
		int rezSumos;
		
		for (int i = 0; i <= this.getDegree(); i++)
		{
			tempRezultatas = new KunoElementas(0);
			pk1 = this.getElement(i);
			
			for (int j = 0; j <= kunoElem.getDegree(); j++)
			{
				pk2 = kunoElem.getElement(j);
				rezSandaugos = sandaugaModuliu(pk1, pk2);
				rezSumos = i+j;
				tempRezultatas.setElement(rezSumos, rezSandaugos);
			}
			rezultatas = rezultatas.suma(tempRezultatas);
		}
		
		rezultatas = rezultatas.dalybaMod(getPrimitive());
		return rezultatas;
	}
		
	/** Is turimo elemento dalinamas kuno elementas elem.
	 * Grazinamas gautas dalmuo.*/
	public KunoElementas dalybaDiv(KunoElementas elem) 
	{
		KunoElementas nulinis = new KunoElementas(0);
		KunoElementas ret = null;
		if (!elem.equals(nulinis))
		{
			ret = this.sandauga(elem.atvirkstinis());
		}
		return ret;
	}
	
	/** Surandamas turimo kuno elemento atvirkstis kuno elementas.*/
	private KunoElementas atvirkstinis() 
	{
		KunoElementas vienetas = new KunoElementas(1);
		KunoElementas ret = new KunoElementas(1);
		
		while (!this.sandauga(ret).equals(vienetas))
		{
			ret.incElemInVector();
		}
		return ret;
	}
	
	/** Padidinama kuno elemento reiksme. */
	public void increment()
	{
		incElemInVector();
	}

	/** Didinama kuno elemento reiksme taip kad neiseitu is
	 * kuno pagrindo ribu. */
	private void incElemInVector() 
	{
		int temp;
		for (int i = 0; i < elementKoef.size(); i++)
		{
			temp = elementKoef.get(i);
			if (sumaModuliu(temp, 1) != 0)
			{
				elementKoef.set(i, temp+1);
				break;
			}
			else if (i == elementKoef.size() - 1)
			{
				elementKoef.set(i, 0);
				break;
			}
			else
			{
				elementKoef.set(i, 0);
			}
		}
	}

	/** Is turimo kuno elemento dalinamas duotas kuno elementas kelem.
	 * Grazinama dalybos liekana.
	 * igyvendinamas dalybos kampu algoritmas 
	 * is http://en.wikipedia.org/wiki/Finite_field_arithmetic#Multiplication*/
	public KunoElementas dalybaMod(KunoElementas kelem)
	{		
		KunoElementas liekana = this.copy();
		KunoElementas daliklis = kelem.copy();
		
		int skirtumas = 0;
		int ls = 0; //laipsniu skirtumas
		int liekanaPr = 0;
		int daliklisPr = 0;
		
		ls = liekana.getSize() - daliklis.getSize();
		for (int k = ls; k >= 0; k--)
		{
			daliklisPr = daliklis.getSize() - 1;
			liekanaPr = liekana.getSize() - (ls-k) - 1;
			while (liekana.getElement(liekanaPr) != 0)
			{
				for (int i = daliklisPr,
						j = liekanaPr;
						(i >= 0) && (j >= 0); 
						i--, j--)
				{
					skirtumas = atimtisModuliu(liekana.getElement(j), 
							daliklis.getElement(i));
					liekana.setElement(j, skirtumas);			
				};
			}
		}
		/** Dalinant vektorius issiplecia, tad reikia
		 * apkarpyti jo ilgi, kad tilptu i kuno elementu rezius. */
		if (getSize() >= daliklis.getSize())
			liekana.elementKoef.setSize(daliklis.getSize()-1);
		return liekana;
	}

	/** Grazinama turimo elemeto kopija.*/
	public KunoElementas copy() 
	{
		KunoElementas ret = new KunoElementas(0);
		for (int i = 0; i < this.getSize(); i++)
		{
			ret.setElement(i, this.getElement(i));
		}
		return ret;
	}


	/** Kuno elementas konvertuojamas i String..*/
	@Override
	public String toString() 
	{
		return getElementAsVector().toString();
	}

	/** i i-taja kuno elemento vieta irasomas elementas*/
	public void setElement(int i, int elementas) 
	{
		for (int j = this.elementKoef.size(); j < i + 1; j++)
		{
			elementKoef.add(0);
		}
		this.elementKoef.set(i, elementas);
	}

	/** Grazinamas kuno elemento koeficientu skaicius.*/
	public int getSize() 
	{
		return elementKoef.size();
	}

	/** Grazinamas didziausias kuno elemento laipsnis 
	 * su nenuliniu indeksu.*/
	public int getDegree() 
	{
		int degree = getSize() - 1;
		for (int i = degree; i >= 0; i--)
		{
			if (getElement(i) == 0)
				degree--;
			else
				break;
		}
		return degree;
	}

	/** Grazinamas i-tasis elementas.*/
	private int getElement(int i)
	{
		if (i >= getSize())
			return 0;
		else
			return elementKoef.get(i);
	}

	/** Ar turimas kuno elementas sutampa su duotuoju ke.
	 * Grazinama true/false.*/
	public boolean equals(KunoElementas ke)
	{
		boolean ret = false;
		
		if (ke.getDegree() == this.getDegree())
		{
			for (int i = 0; i < ke.getSize(); i ++)
			{
				if (ke.getElement(i) == (this.getElement(i)))
				{
					ret = true;
				}
				else 
				{
					ret = false;
					break;
				}
			}
		}
		
		return ret;
	}
}
