/*
 * Copyright 2015, Anthony Urso, Hridesh Rajan, Robert Dyer,
 *                 and Iowa State University of Science and Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package boa.compiler;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class Test {
  public static void main(String[] args) throws IOException, URISyntaxException
  {
    Configuration configuration = new Configuration();
    FileSystem hdfs = FileSystem.get(new URI("hdfs://localhost:54310"), configuration);
    FileStatus[] fileStatus = hdfs.listStatus(new Path("hdfs://localhost:54310/ast/"));
    Path[] paths = FileUtil.stat2Paths(fileStatus);
    System.out.println("***** Contents of the Directory *****");
    for(Path path : paths)
    {
      System.out.println(path);
    }
  }
}
