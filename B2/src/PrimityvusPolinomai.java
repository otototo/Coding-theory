
import java.util.HashMap;
import java.util.Vector;

/** Israsyti visi primityvus polinomai ir issaugoti kaip
 * klases KunoElementas kintamieji.  */
public class PrimityvusPolinomai 
{
	private HashMap<Integer, Vector<KunoElementas>> primitiveKunoElementas;
	
	/** Inicijuoja primityvius polinomus */
	public PrimityvusPolinomai() 
	{
		Vector<KunoElementas> tempv = new Vector<KunoElementas>();
		primitiveKunoElementas = new HashMap<Integer, Vector<KunoElementas>>();
		
		addP2Primitives(tempv);
		primitiveKunoElementas.put(2, tempv);
		tempv = new Vector<KunoElementas>();		
		
		addP3Primitives(tempv);
		primitiveKunoElementas.put(3, tempv);
		tempv = new Vector<KunoElementas>();
		
		addP5Primitives(tempv);
		primitiveKunoElementas.put(5, tempv);
		tempv = new Vector<KunoElementas>();
		
		addP7Primitives(tempv);
		primitiveKunoElementas.put(7, tempv);
		tempv = new Vector<KunoElementas>();
		
		addP11Primitives(tempv);
		primitiveKunoElementas.put(11, tempv);
		tempv = new Vector<KunoElementas>();
		
		addP13Primitives(tempv);
		primitiveKunoElementas.put(13, tempv);
		tempv = new Vector<KunoElementas>();
		
		addP17Primitives(tempv);
		primitiveKunoElementas.put(17, tempv);
		tempv = new Vector<KunoElementas>();
		
		addP19Primitives(tempv);
		primitiveKunoElementas.put(19, tempv);
		tempv = new Vector<KunoElementas>();
		
		addP23Primitives(tempv);
		primitiveKunoElementas.put(23, tempv);
		tempv = new Vector<KunoElementas>();
	}

	/** Israso pagrindo 23 primityvus. */
	private void addP23Primitives(Vector<KunoElementas> tempv) 
	{
		//19+22x+x^2
		KunoElementas tempp = new KunoElementas(19,22,1);
		tempv.add(tempp);		
	}

	/** Israso pagrindo 19 primityvus. */
	private void addP19Primitives(Vector<KunoElementas> tempv) 
	{
		//2+x+x^2
		KunoElementas tempp = new KunoElementas(2,1,1);
		tempv.add(tempp);		
	}

	/** Israso pagrindo 17 primityvus. */
	private void addP17Primitives(Vector<KunoElementas> tempv) 
	{
		//10+x+x^2
		KunoElementas tempp = new KunoElementas(10,1,1);
		tempv.add(tempp);			
	}

	/** Israso pagrindo 13 primityvus. */
	private void addP13Primitives(Vector<KunoElementas> tempv) 
	{
		//2+x+x^2
		KunoElementas tempp = new KunoElementas(2,1,1);
		tempv.add(tempp);		
	}

	/** Israso pagrindo 11 primityvus. */
	private void addP11Primitives(Vector<KunoElementas> tempv)
	{
		//7+x+x^2
		KunoElementas tempp = new KunoElementas(7,1,1);
		tempv.add(tempp);			
	}

	/** Israso pagrindo 7 primityvus. */
	private void addP7Primitives(Vector<KunoElementas> tempv) 
	{
		//3+x+x^2
		KunoElementas tempp = new KunoElementas(3,1,1);
		tempv.add(tempp);			
		//2+x+x^2+x^3
		tempp = new KunoElementas(2,1,1,1);
		tempv.add(tempp);		
	}

	/** Israso pagrindo 5 primityvus. */
	private void addP5Primitives(Vector<KunoElementas> tempv) 
	{
		//2+x+x^2
		KunoElementas tempp = new KunoElementas(2,1,1);
		tempv.add(tempp);			
		//2+x^2+x^3
		tempp = new KunoElementas(2,0,1,1);
		tempv.add(tempp);			
		//3+x+x^3+x^4
		tempp = new KunoElementas(3,1,0,1,1);
		tempv.add(tempp);			
	}

	/** Israso pagrindo 3 primityvus. */
	private void addP3Primitives(Vector<KunoElementas> tempv) 
	{
		//1+x
		KunoElementas tempp = new KunoElementas(1,1);
		tempv.add(tempp);			
		//2+x+x^2
		tempp = new KunoElementas(2,1,1);
		tempv.add(tempp);			
		//1+2x^2+x^3
		tempp = new KunoElementas(1,0,2,1);
		tempv.add(tempp);				
		//2+x^3+x^4
		tempp = new KunoElementas(2,0,0,1,1);
		tempv.add(tempp);				
		//1+x^2+x^4+x^5
		tempp = new KunoElementas(1,0,1,0,1,1);
		tempv.add(tempp);				
		//2+x^5+x^6
		tempp = new KunoElementas(2,0,0,0,0,1,1);
		tempv.add(tempp);	
	}

	/** Israso pagrindo 2 primityvus. */
	private void addP2Primitives(Vector<KunoElementas> tempv) 
	{
		//1+x
		KunoElementas tempp = new KunoElementas(1,1);
		tempv.add(tempp);
		//1+x+x^2
		tempp = new KunoElementas(1, 1, 1);
		tempv.add(tempp);
		//1+x+x^3
		tempp = new KunoElementas(1, 1, 0, 1);		
		tempv.add(tempp);
		//1+x+x^4
		tempp = new KunoElementas(1, 1, 0, 0, 1);
		tempv.add(tempp);
		//1+x^2+x^5
		tempp = new KunoElementas(1, 0, 1, 0, 0, 1);
		tempv.add(tempp);
		//1+x+x^6
		tempp = new KunoElementas(1, 1, 0, 0, 0, 0, 1);
		tempv.add(tempp);		
		//1+x+x^7
		tempp = new KunoElementas(1, 1, 0, 0, 0, 0, 0, 1);
		tempv.add(tempp);
		//1+x^2+x^3+x^4+x^8
		tempp = new KunoElementas(1, 0, 1, 1, 1, 0, 0, 0, 1);
		tempv.add(tempp);
		//1+x^4+x^9
		tempp = new KunoElementas(1, 1, 0, 0, 1, 0, 0, 0, 0, 1);
		tempv.add(tempp);
		//1+x^3+x^10
		tempp = new KunoElementas(1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1);
		tempv.add(tempp);
	}

	/** Grazina primityva kurio pagrindas p ir yra m-tasis sarase.*/
	public KunoElementas getPrimitive(int p, int m) 
	{
		Vector<KunoElementas> vk = primitiveKunoElementas.get(p);
		KunoElementas k = vk.get(m-1);
		return k;
	}
}
