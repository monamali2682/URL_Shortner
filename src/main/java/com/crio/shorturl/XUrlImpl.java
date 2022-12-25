package com.crio.shorturl;
import java.util.HashMap;
import java.util.Random;
import java.lang.StringBuilder;

public class XUrlImpl implements XUrl{

  HashMap <String,String> urlMap = new HashMap<>();
  HashMap <String,Integer> hitCountMAp = new HashMap<>();

  public String getShortUrl(String longUrl){
    Random rand = new Random();
    String randChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    StringBuilder shortUrl = new StringBuilder("http://short.url/");
    char[] chars = new char[9];
    for(int i=0;i<9;i++){
      chars[i] = randChars.charAt(rand.nextInt(9));
    }
    shortUrl.append(new String(chars));
    return shortUrl.toString();
  }


    // If longUrl already has a corresponding shortUrl, return that shortUrl
  // If longUrl is new, create a new shortUrl for the longUrl and return it
  public String registerNewUrl(String longUrl){
    if(urlMap.containsKey(longUrl)){
      return urlMap.get(longUrl);
    }
    String shortUrl = getShortUrl(longUrl);
    urlMap.put(shortUrl,longUrl);
    urlMap.put(longUrl,shortUrl);
    return shortUrl;
  }

  // If shortUrl is already present, return null
  // Else, register the specified shortUrl for the given longUrl
  // Note: You don't need to validate if longUrl is already present, 
  //       assume it is always new i.e. it hasn't been seen before 
  public String registerNewUrl(String longUrl, String shortUrl){
    String ans= new String();
    if(urlMap.containsKey(shortUrl)){
      ans=null;
      return ans;
    }
    urlMap.put(longUrl,shortUrl);
    urlMap.put(shortUrl,longUrl);
    ans = shortUrl;
    return ans;
  }





  // If shortUrl doesn't have a corresponding longUrl, return null
  // Else, return the corresponding longUrl
  public String getUrl(String shortUrl){
    String ans= new String();
    if(urlMap.containsKey(shortUrl)){
      String longUrl = urlMap.get(shortUrl);
      hitCountMAp.put(longUrl, hitCountMAp.getOrDefault(longUrl, 0)+1);
      return longUrl;
    }
    ans = null;
    return ans;
  }

  // Return the number of times the longUrl has been looked up using getUrl()
  public Integer getHitCount(String longUrl){
    if(hitCountMAp.containsKey(longUrl)){
      return hitCountMAp.get(longUrl);
    }
    return 0;
  }

  // Delete the mapping between this longUrl and its corresponding shortUrl
  // Do not zero the Hit Count for this longUrl
  public String delete(String longUrl){
    String shortUrl = urlMap.get(longUrl);
    urlMap.remove(shortUrl);
    urlMap.remove(longUrl);
    return "";
  }

}