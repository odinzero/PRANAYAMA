
package com.pranayama.ui.filechooser;

import java.io.File;
import javax.swing.filechooser.*;

public class fileFilter extends FileFilter {
    
    String[] extensions;
    String   description;
   
    
    public fileFilter(String ext){
       this( new String[] {ext} , null); 
    }
    
    public fileFilter(String[] exts , String descr){
       // we do clone and lowercase to extensions
       extensions = new String[exts.length];
       for( int i = exts.length - 1; i >=  0; i--){
            extensions[i] = exts[i].toLowerCase();  
       }
       description = ( descr == null ? exts[0] + " files" : descr);
    }

    @Override
    public boolean accept(File pathname) {
         // We always allow directories, regardless of their extensions.
         if( pathname.isDirectory() )  { return true ;}
         // It's a regular file, so check the extension.
         String name = pathname.getName().toLowerCase();
          for( int i = extensions.length - 1; i >=  0; i--){
             if(name.endsWith(extensions[i])) {
                 return true;
             } 
          }    
                 return false;
    }
    
      public String getDescription( ) { return description; }
}
