package com.plivo.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Rajat.Kachhara on 07-10-2016.
 */
public class PropertyReader {
      /**
         * get all the properties value present in configuration.properties
         * @return hash map consisting all properties in key.value pair
         */
        public synchronized HashMap<String, String> getPropValues(String file)
        {
            HashMap<String, String> result = new HashMap<String, String>();

            try
            {
                Properties prop = new Properties();
                //String propFileName = "config.properties";

                InputStream inputStream= getClass().getClassLoader().getResourceAsStream(file);

                if (inputStream != null)
                {
                    prop.load(inputStream);
                }
                else
                {
                    throw new FileNotFoundException("Property file '" + file + "' not found in the classpath");
                }

                // get the property values
                Set propNames = prop.stringPropertyNames();
                Iterator<String> iterator = propNames.iterator();
                while (iterator.hasNext())
                {
                    String key = iterator.next();
                    result.put(key , prop.getProperty(key));
                }

                inputStream.close();

                return result;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }
}
           /* try
            {
               System.out.println(file);
                ResourceBundle bundle = ResourceBundle.getBundle(file);
                // get the property values

                Enumeration<String> keys = bundle.getKeys();

                while (keys.hasMoreElements()) {
                    String key = keys.nextElement();
                    System.out.println(key);
                    result.put(key, bundle.getString(key));
                    System.out.println(bundle.getString(key));
                }

                return result;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }*/



