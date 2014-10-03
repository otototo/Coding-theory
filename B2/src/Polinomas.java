import java.util.Vector;

/** Apibrezia polinoma. Ir veiksmus tarp polinomu. */
public class Polinomas 
{
	private Vector<KunoElementas> koeficientai;

	/**Inicijuojamas tuscias polinomas*/
	public Polinomas()
	{
		koeficientai = new Vector<KunoElementas>();
	}
	

	/**Inicijuojamas polinomas su duotais koeficientais.
	 * Kiekvienas ju konvertuojamas i kuno elementa.
	 * Laipsniai eina is kaires i desine didejimo tvarka.
	 * Pvz.: skaitomas polinomas: 1*x^0+2*x^1+0*x^3+1*x^4
	 * in: koeficientai - polinomo koeficientai prie x.
	 * Pvz.: perduoti koeficientai: [1,2,0,1]*/
	public Polinomas(int ...koeficientai) 
	{
		this.koeficientai = new Vector<KunoElementas>();
		for (int i : koeficientai)
		{
			addKoef(new KunoElementas(i));
		}
	}

	/**Inicijuojamas polinomas su duotais koeficientais.
	 * Laipsniai eina is kaires i desine didejimo tvarka.
	 * Pvz.: skaitomas polinomas: [1]*x^0+[2]*x^1+[0]*x^3+[1]*x^4
	 * in: koeficientai - polinomo koeficientai prie x, 
	 * 	kurie jau yra kuno elementais.
	 * Pvz.: perduoti koeficientai: [[1],[2],[0],[1]]*/
	public Polinomas(KunoElementas ...koeficientai) 
	{
		this.koeficientai = new Vector<KunoElementas>();
		for (KunoElementas i : koeficientai)
		{
			addKoef(i);
		}
	}

	/**iraso duota koeficienta prie sekancio auksciausio polinomo laipsnio.*/
	public void addKoef(KunoElementas coef) 
	{
		koeficientai.add(coef);
	}


	/**Perraso polinomo koeficienta i-tojoje vietoje*/
	public void setKoef(int i, KunoElementas coef) 
	{
		koeficientai.set(i, coef);		
	}
	

	/**Grazina pasirinkta polinomo koeficienta*/
	public KunoElementas getKoef(int i)
	{
		if (i >= koeficientai.size()) 
			return new KunoElementas(0);
		else 
			return koeficientai.get(i);
	}

	/**Grazina visus polinomo koeficientus*/
	public Vector<KunoElementas> getKoefs()
	{
		return koeficientai;
	}

	/**Kovertuoja polinoma i String.*/
	@Override
	public String toString() 
	{
		String ret = "";
		int deg = getDegree();
		for (int i = deg; i >= 0; i--) {
			if (koeficientai.get(i).nelygu(0))
			{
				if (!ret.equalsIgnoreCase("")) ret +=" + ";
				ret += koeficientai.get(i).toString() + "x^" + i;
			}
		}
		if (getDegree() == -1)
		{
			ret = "0x^0";
		}
		return ret;
	}


	/**Grazina koeficientu skaiciu. Priskaiciuoja ir nulinius.*/
	public int getSize() 
	{
		return koeficientai.size();
	}

	/**Inicijuoja polinoma, kurio auksciausias laipsnis (ilgis - 1).*/
	public void initEmpty(int ilgis) 
	{
		koeficientai.removeAllElements();
		for (int i = 0; i < ilgis; i++)
			addKoef(0);
	}

	/**iraso duota koeficienta prie sekancio auksciausio polinomo laipsnio.*/
	public void addKoef(int i)
	{
		addKoef(new KunoElementas(i));		
	}

	/**Grazina auksciausia laipsniu su nenuliniu koeficientu.*/
	public int getDegree() 
	{
		int degree = getSize() - 1;
		for (int i = degree; i >= 0; i--)
		{
			if (getKoef(i).lygu(0))
				degree--;
			else
				break;
		}
		return degree;
	}

	/**Dvieju polinomu palyginimas.*/
	public boolean equals(Polinomas p) 
	{
		boolean ret = false;
		
		if (p.getDegree() == this.getDegree())
		{
			for (int i = 0; i < p.getSize(); i ++)
			{
				if (p.getKoef(i).equals(this.getKoef(i)))
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

	/**Padaroma polinomo kopija.*/
	public Polinomas copy() {
		Polinomas ret = new Polinomas();
		for (int i = 0; i < this.getSize(); i++)
		{
			ret.addKoef(this.getKoef(i));
		}
		return ret;
	}

	/**Palyginama ar esamas polinomas mazesnis uz perduota fx.*/
	public boolean maziauUz(Polinomas fx) 
	{
		boolean ret = false;
		
		if (this.getDegree() < fx.getDegree())
			ret = true;
		else if (this.getDegree() == fx.getDegree())
		{
			for (int i = getSize() - 1; i >= 0; i--)
			{
				if (this.getKoef(i).maziauUz(fx.getKoef(i)))
				{
					ret = true;
					break;
				}
				else if (this.getKoef(i).daugiauUz(fx.getKoef(i)))
				{
					break;
				}
			}
		}		
		return ret;
	}
    	
	/** Polinomu atimtis. 
	 * Is turimo polinomo atimamas perduotas polinomas
	 * Grazinamas rezultatas*/
	public Polinomas atimtis(Polinomas polinomas)
	{
		Polinomas rezultatas = new Polinomas();
		int max = Math.max(this.getSize(), polinomas.getSize());
		KunoElementas rez;
		for (int i = 0; i < max; i++) {
			rez =  this.getKoef(i).atimtis(polinomas.getKoef(i)); 
			rezultatas.addKoef(rez);
		}
		return rezultatas;
	}

	/** Polinomu sandauga. 
	 * Is turimo polinomo padauginamas perduotas polinomas
	 * Grazinamas rezultatas*/
	public Polinomas sandauga(Polinomas polinomas) 
	{		
		Polinomas rezultatas = new Polinomas(); 
		int dydis = this.getDegree()+polinomas.getDegree() + 1;
		rezultatas.initEmpty(dydis);
		Polinomas tempRezultatas = null;		
		KunoElementas pk1, pk2, rezSandaugos;
		int rezSumos;
		
		for (int i = 0; i <= this.getDegree(); i++)
		{
			tempRezultatas = new Polinomas();
			tempRezultatas.initEmpty(dydis);
			pk1 = this.getKoef(i);
			
			for (int j = 0; j <= polinomas.getDegree(); j++)
			{
				pk2 = polinomas.getKoef(j);
				rezSandaugos = pk1.sandauga(pk2);
				rezSumos = i+j;
				tempRezultatas.setKoef(rezSumos, rezSandaugos);
			}
			rezultatas = rezultatas.suma(tempRezultatas);
		}
		return rezultatas;
	}
	
	/** Polinomu suma. 
	 * Prie turimo polinomo pridedamas perduotas polinomas
	 * Grazinamas rezultatas*/
	public Polinomas suma(Polinomas polinomas) 
	{				
		int max = Math.max(this.getSize(), polinomas.getSize());	
		KunoElementas rez;
		Polinomas rezultatas = new Polinomas();
		for (int i = 0; i < max; i++) 
		{
			rez = this.getKoef(i).suma(polinomas.getKoef(i));
			rezultatas.addKoef(rez);
		}
		return rezultatas;
	}	

	
	/** Polinomu dalyba kampu. 
	 * Is turimo polinomo padalinamas perduotas polinomas
	 * Grazinamas rezultatas susideda is dvieju daliu {dalmuo, liekana}*/
	public Vector<Polinomas> dalyba(Polinomas polinomas)
	{
		Vector<Polinomas> rezultatas = new Vector<Polinomas>();
		Polinomas dalmuo = new Polinomas();
		
		Polinomas liekana = this.copy();
		Polinomas daliklis = polinomas.copy();
		Polinomas temp = new Polinomas();
		
		Polinomas naujasDalmuo = new Polinomas();
		int ls = 0; //laipsniu skirtumas
		KunoElementas ks = new KunoElementas(0); // koeficientu santykis
		
		while (!liekana.maziauUz(daliklis))
		{
			ls = liekana.getDegree() - daliklis.getDegree();
			ks = liekana.getKoef(liekana.getDegree()).dalybaDiv(
					daliklis.getKoef(daliklis.getDegree())); 
			
			naujasDalmuo.initEmpty(ls + 1); // polinomo ilgis
			naujasDalmuo.setKoef(ls, ks);

			temp = naujasDalmuo.sandauga(daliklis);
			liekana = liekana.atimtis(temp);
			dalmuo = dalmuo.suma(naujasDalmuo);
		}		
		rezultatas.add(dalmuo);
		rezultatas.add(liekana);
		return rezultatas;
	}

	/** i i-taja vieta irasomas koeficientas k */
	public void setKoef(Integer i, int k) 
	{
		setKoef(i, new KunoElementas(k));		
	}

	/** Is polinomo atimamas kuno elementas 
	 * (funkcijoje paverciamas i Polinoma, su nuliniu laipsniu). */
	public Polinomas atimtis(KunoElementas qi) 
	{		
		return atimtis(new Polinomas(qi));
	}

	/** Tikrinama ar polinomas = 0.
	 * Ar auksciausias laipsnis = 0 ir
	 * koeficientas prie jo = 0.*/
	public boolean yraNulis() {
		boolean ret = true;
		for (int i = 0; i < koeficientai.size(); i++)
		{
			if (!koeficientai.get(i).lygu(0)) {
				ret = false;
				break;
			}
		}
		return ret;
	}
}
