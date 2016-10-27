package boa.compiler;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.util.ReflectionUtils;

import boa.compiler.ast.Program;
import boa.functions.BoaAstIntrinsics;
import boa.functions.BoaGraphIntrinsics;
import boa.graphs.cfg.CFG;
import boa.types.Ast.ASTRoot;
import boa.types.Ast.Declaration;
import boa.types.Ast.Method;
import boa.types.Ast.Namespace;
import boa.types.Ast.Type;
import boa.types.Code.CodeRepository;
import boa.types.Code.Revision;
import boa.types.Diff.ChangedFile;
import boa.types.Toplevel.Project;

public class DatasetVisitor {

	public void start(Program n) {
		Configuration conf = new Configuration();
		Path path = new Path("/home/ram/Desktop/work-test/tmprepcache/projects.seq");
		FileSystem fs;
		SequenceFile.Reader sequenceFileReader = null;

		try {
			fs = FileSystem.get(conf);
			sequenceFileReader = new SequenceFile.Reader(fs, path, conf);
		} catch (IOException e) {
			System.out
					.println("Exception occured in Program node while creating FileSystem or Reader");
		}
		System.out
				.println("Class of ----+>" + sequenceFileReader.getKeyClass());
		org.apache.hadoop.io.Text key = (org.apache.hadoop.io.Text) ReflectionUtils
				.newInstance(sequenceFileReader.getKeyClass(), conf);
		org.apache.hadoop.io.BytesWritable keyValue = (org.apache.hadoop.io.BytesWritable) ReflectionUtils
				.newInstance(sequenceFileReader.getValueClass(), conf);

		boa.types.Toplevel.Project _input = null;
		for (int projN = 0; projN < 1; projN++) {
			System.out.println("---\n\n\n\n\tITERATION #" + projN
					+ "\t---\n\n\n\n\n\n");

			try {

				sequenceFileReader.next(key, keyValue);
				_input = boa.types.Toplevel.Project
						.parseFrom(com.google.protobuf.CodedInputStream
								.newInstance(keyValue.getBytes(), 0,
										keyValue.getLength()));
				visit(_input);
			} catch (Exception e) {
				System.out
						.println("Exception occured in Program node while reading projectr");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Provides a default action for pre-visiting nodes. Any
	 * <code>preVisit()</code> method that is not overridden calls this method.
	 * 
	 * @return always returns true
	 */
	protected boolean defaultPreVisit() throws Exception {
		return true;
	}

	protected boolean preVisit(final Project node) throws Exception {
		return defaultPreVisit();
	}

	protected boolean preVisit(final CodeRepository node) throws Exception {
		return defaultPreVisit();
	}

	protected boolean preVisit(final Revision node) throws Exception {
		return defaultPreVisit();
	}

	protected boolean preVisit(final ChangedFile node) throws Exception {
		return defaultPreVisit();
	}

	protected boolean preVisit(final ASTRoot node) throws Exception {
		return defaultPreVisit();
	}

	protected boolean preVisit(final Namespace node) throws Exception {
		return defaultPreVisit();
	}

	protected boolean preVisit(final Declaration node) throws Exception {
		return defaultPreVisit();
	}

	protected boolean preVisit(final Type node) throws Exception {
		return defaultPreVisit();
	}

	protected boolean preVisit(final Method node) throws Exception {
		return defaultPreVisit();
	}

	/**
	 * Provides a default action for post-visiting nodes. Any
	 * <code>postVisit()</code> method that is not overridden calls this method.
	 */
	protected void defaultPostVisit() throws Exception {
	}

	protected void postVisit(final Project node) throws Exception {
		defaultPostVisit();
	}

	protected void postVisit(final CodeRepository node) throws Exception {
		defaultPostVisit();
	}

	protected void postVisit(final Revision node) throws Exception {
		defaultPostVisit();
	}

	protected void postVisit(final ChangedFile node) throws Exception {
		defaultPostVisit();
	}

	protected void postVisit(final ASTRoot node) throws Exception {
		defaultPostVisit();
	}

	protected void postVisit(final Namespace node) throws Exception {
		defaultPostVisit();
	}

	protected void postVisit(final Declaration node) throws Exception {
		defaultPostVisit();
	}

	protected void postVisit(final Type node) throws Exception {
		defaultPostVisit();
	}

	protected void postVisit(final Method node) throws Exception {
		defaultPostVisit();
	}

	public final void visit(final Project node) throws Exception {
		if (preVisit(node)) {
			final List<CodeRepository> reposList = node
					.getCodeRepositoriesList();
			final int reposSize = reposList.size();
			for (int i = 0; i < reposSize; i++) {
				visit(reposList.get(i));
			}
			postVisit(node);
		}
	}

	public final void visit(final CodeRepository node) throws Exception {
		if (preVisit(node)) {
			for(ChangedFile cf:BoaAstIntrinsics.getSnapshot(node)) {
				visit(cf);
			}
			//final List<Revision> revisionsList = node.getRevisionsList();
			//final int revisionsSize = revisionsList.size();
			//System.out.println("revsision size" + revisionsSize);
			//for (int i = 0; i < revisionsSize; i++)
			//	visit(revisionsList.get(i));
			//visit(revisionsList.get(revisionsSize-1));
			postVisit(node);
		}
	}

	public final void visit(final Revision node) throws Exception {
		if (preVisit(node)) {
			final List<ChangedFile> filesList = node.getFilesList();
			final int filesSize = filesList.size();
			//System.out.println("file size" + filesSize);
			for (int i = 0; i < filesSize; i++) {
				System.out.println("file name" + filesList.get(i).getName());
				visit(filesList.get(i));
			}

			postVisit(node);
		}
	}

	ChangedFile currChfl = null;

	public final void visit(final ChangedFile node) throws Exception {
		this.currChfl = node;
		if (preVisit(node)) {
			visit(BoaAstIntrinsics.getast(node));

			postVisit(node);
			this.currChfl = null;
		}
	}

	public final void visit(final ASTRoot node) throws Exception {
		if (preVisit(node)) {
			final List<Namespace> namespacesList = node.getNamespacesList();
			final int namespacesSize = namespacesList.size();
				System.out.println(" namespace size ----------------------- "+namespacesSize);
			for (int i = 0; i < namespacesSize; i++)
				visit(namespacesList.get(i));

			postVisit(node);
		}
	}

	public final void visit(final Namespace node) throws Exception {
		if (preVisit(node)) {
			final List<Declaration> declarationsList = node
					.getDeclarationsList();
			final int declarationsSize = declarationsList.size();
			for (int i = 0; i < declarationsSize; i++)
				visit(declarationsList.get(i));

			postVisit(node);
		}
	}

	Stack<String> class_names = new Stack<String>();

	public final void visit(final Declaration node) throws Exception {
		if (preVisit(node)) {
			class_names.push(node.getName());
			final List<Method> methodsList = node.getMethodsList();
			final int methodsSize = methodsList.size();
			System.out.println("method size "+methodsSize);
			for (int i = 0; i < methodsSize; i++)
				visit(methodsList.get(i));

			postVisit(node);
			class_names.pop(); // disgard the current class
		}
	}

	public final void visit(final Type node) throws Exception {
		if (preVisit(node)) {
			postVisit(node);
		}
	}

	final String keyDlim = "!!";
	SequenceFile.Writer w = null;

	public final void visit(final Method node) throws Exception {
		
		if (preVisit(node)) {
			System.out.println("--------------------------------------------------------");
			System.out.println("CFG STARTS "+node.getName());
			if(node.getName().equals("asTestList")) {
//			boa.types.Control.CFG cfg = BoaGraphIntrinsics.getcfg(node);
			//if(node.getName().equals("dead2")) {
			//boa.types.Control.CFG cfg = BoaGraphIntrinsics.getcfg(node);
			System.out.println(BoaGraphIntrinsics.getcfgstring(node));
			//BoaGraphIntrinsics.getcfg1(node);
			//System.out.println(BoaGraphIntrinsics.getdetails(node));			
			}
		}
	}

	public static float nextPrime(float cur_prime) {
		while(true) {
			cur_prime=cur_prime+1;
			int j=2;
			if(cur_prime==2) {
				return cur_prime+0.2f;
			}
			while(j<=cur_prime) {
				if(cur_prime%j==0) {	
					break;
				}
				else if(j==cur_prime-1) {
					return cur_prime+0.2f;			
				}
				j++;
			}		
		}
	}

	public final static void traverse(boa.types.Control.CFG cfg) throws Exception {
		//System.out.println(cfg.toString());
	}
}
