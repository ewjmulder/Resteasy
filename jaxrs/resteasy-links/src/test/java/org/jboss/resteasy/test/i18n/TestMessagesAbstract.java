package org.jboss.resteasy.test.i18n;

import java.util.Locale;

import junit.framework.Assert;

import org.jboss.resteasy.links.i18n.Messages;
import org.jboss.resteasy.test.resteasy_jaxrs.i18n.TestMessagesParent;
import org.junit.Test;

/**
 * 
 * @author <a href="ron.sigal@jboss.com">Ron Sigal</a>
 * @version $Revision: 1.1 $
 *
 * Copyright Aug 28, 2015
 */
abstract public class TestMessagesAbstract extends TestMessagesParent
{
   protected static final String BASE = String.format("0%5s", Messages.BASE).substring(0, 4);

   @Test
   public void testLocale() throws Exception
   {  
      Locale locale = getLocale();
      String filename = "org/jboss/resteasy/links/i18n/Messages.i18n_" + locale.toString() + ".properties";
      if (!before(locale, filename))
      {
         System.out.println(getClass() + ": " + filename + " not found.");
         return;
      }
      
      Assert.assertEquals(getExpected(BASE + "00", "cannotGuessCollectionType"), Messages.MESSAGES.cannotGuessCollectionType());
      Assert.assertEquals(getExpected(BASE + "20", "discoveryFailedForMethod", "class", "method", "s"), Messages.MESSAGES.discoveryFailedForMethod("class", "method", "s"));
      Assert.assertEquals(getExpected(BASE + "35", "failedToInjectLinks", new Integer(17)), Messages.MESSAGES.failedToInjectLinks(new Integer(17)));
      Assert.assertEquals(getExpected(BASE + "60", "notEnoughtUriParameters", 3, 5), Messages.MESSAGES.notEnoughtUriParameters(3, 5));
   }
   
   @Override
   protected int getExpectedNumberOfMethods()
   {
      return Messages.class.getDeclaredMethods().length;  
   }
   
   abstract protected Locale getLocale();
}
