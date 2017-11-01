 /**
	 * CS 301: Numerical Methods
	 * Professor: A. Khakpour
	 *
	 * Computer Exercise: Opening problem of chapter 4 
	 * viscosity of water
	 * 
	 *
	 * @author - Eric Schenck
	 * last modified: November 2, 2017
	 *   
	 */
	
public class ViscosityOfWaterInterpolation {

		public static void main(String[] args){
		
			int j, k, n; int jmax = 0;
			float e, h, p, t;
			float emax = 0; float pmax = 0; float tmax = 0;
			
			n = 4;									// value n
			h = (float)(15.0 / n);								
			
			float[] x = new float[n];				// arrays to be used
			float[] y = new float[n];
			float[] a = new float[n];
			
			for( k = 0; k < n; ++k){
				x[k] = k * h;
				y[k] = (float)Math.sin(x[k]);
			}
			
			Coef(n,x,y,a);							// function call
			
			System.out.print("array a is: {");		// output
			for(int i = 0; i < a.length ; ++i){
				System.out.print(a[i]);
				if( i != a.length - 1 ){
					System.out.print(", ");
				}
			}
			System.out.print("}\n\n");
			
			emax = 0;	
			
			// formatted output
			System.out.print("----------------------------------"
					+ "-----------------------------------------------\n"
					+"|  j  |		t	    |	     p  	|		e		|\n"
					+"--------------------------------------"
					+ "-------------------------------------------\n");
			
			for ( j = 0; j < (4*n); ++j ){
				t = (float)(j*h)/4;
				p = Eval(n,x,a,t);
				e = (float)Math.abs(Math.sin(t) - p);
				
				System.out.printf("|  %d  | \t%.5f\t   | \t%.5f\t\t  "
						+ "| \t%.5f\t\t  |\n", j, t, p, e);
				
				if( e > emax){
					jmax = j;
					tmax = t;
					pmax = p;
					emax = e;
				}
			}
			
			System.out.printf("----------------------------------"
					+ "-----------------------------------------\n"
					+"------------------------------------"
					+ "---------------------------------------\n"		
					+"| jmax = %d | tmax = %.5f	| pmax = %.5f  |	emax = %.5f	  |\n"
					+"----------------------------------"
					+ "-----------------------------------------\n", 
					jmax, tmax, pmax, emax);
		}
		
		public static void Coef(int n, float[] x, float[] y, float[] a){
			
			for(int i = 0; i < n ; ++i){
				a[i] = y[i];
			}
			
			for(int j = 1; j < n; ++j){
				for(int i = n-1; i > j; --i) {
					
					a[i] = (a[i] - a[i-1]) / (x[i] - x[i-1]);
					
				}
			}
		}
		
		public static float Eval(int n, float[] x, float[]a, float t){
			
			float temp = a[n-1];
			
			for(int i = n-1; i > 0 ; --i){
				temp = (temp)*(t-x[i]) + a[i];
			}
			
			return temp;
		}
	}
