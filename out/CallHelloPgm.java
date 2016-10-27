import java.io.*;
 
public class CallHelloPgm
{
	public static void main(String args[]) {
		Process theProcess = null;
		BufferedReader inStream = null;

		System.out.println("CallHelloPgm.main() invoked");
		int count=3;
		long average=0;
		try {
			for(int i=0;i<count;i++) {
				long time = System.currentTimeMillis();
				theProcess = Runtime.getRuntime().exec("java -cp lib/boa-runtime.jar:lib/commons-configuration-1.7.jar:lib/hadoop-core-1.0.4.jar:lib/commons-lang-2.4.jar:lib/commons-math-2.1.jar:lib/antlr-runtime-4.5.jar:lib/log4j-1.2.15.jar:lib/protobuf-java-2.5.0.jar:lib/scannotation-1.0.3.jar:lib/ST-4.0.8.jar:lib/commons-cli-1.2.jar:lib/org-apache-commons-logging.jar:. boa.waitnotinloop /home/ram/Desktop/work-dacapo/tmprepcache/projects.seq /home/ram/Desktop/compiler-git-seq/compiler-local/output"+i+".txt");
				//theProcess = Runtime.getRuntime().exec("java -cp lib/boa-runtime.jar:lib/commons-configuration-1.7.jar:lib/hadoop-core-1.0.4.jar:lib/commons-lang-2.4.jar:lib/commons-math-2.1.jar:lib/antlr-runtime-4.5.jar:lib/log4j-1.2.15.jar:lib/protobuf-java-2.5.0.jar:lib/scannotation-1.0.3.jar:lib/ST-4.0.8.jar:lib/commons-cli-1.2.jar:lib/org-apache-commons-logging.jar:. boa.Job0 /home/ram/Desktop/work-qualitass/tmprepcache/projects.seq /home/ram/Desktop/compiler-git-seq/compiler-local/output"+i+".txt");
				theProcess.waitFor();
				long cur_time = System.currentTimeMillis()-time;
				System.out.println(cur_time);
				average +=cur_time;
			}
				System.out.println("average " + average/count);
		}
		catch(IOException e) {
			System.err.println("Error on exec() method");
			e.printStackTrace();  
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
