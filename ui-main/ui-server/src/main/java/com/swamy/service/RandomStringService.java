package com.swamy.service;

import java.util.logging.Level;

import com.swamy.dao.BasicDAO;
import com.swamy.dm.RandomPattern;
import com.swamy.exception.DBNotAvailableException;
import com.swamy.exception.DataNotFoundException;
import com.swamy.exception.DataNotSavedException;
import com.swamy.exception.GenericException;
import org.apache.commons.math.random.RandomDataImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * User: pvam
 * Date: May 1, 2011
 * Time: 12:05:15 PM * 
 */
public class RandomStringService
{
    private static Logger logger = Logger.getLogger(RandomStringService.class.getName());

    static char[] allowedChars = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
            , 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            , '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    static RandomDataImpl rand = new RandomDataImpl();
    static BasicDAO dao = new BasicDAO();
    private static final int START_LENGHT = 4;
    private static final int PAT_LENGHT = 47;
    private static final String RAND_COLL = "random_coll";

    static
    {
        //RandomStringDAO dao = DAOFactory.getRandomStringDAO();
        //RandomString rs = dao.getRandomStoreValue();
        rand.reSeedSecure();
        //DAOFactory.getRandomPatternDAO().
    }


    public static String getRandomTopicName() throws GenericException
    {
        try
        {
            return getRandomString();
        }
        catch (Exception e) {
            throw new GenericException(e);
        } catch (DBNotAvailableException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (DataNotSavedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public static RandomPattern initpattern() throws DataNotSavedException, DBNotAvailableException
    {
        logger.log(Level.INFO,"Initalizing the pattern by creating a new one..");
        //No pattern, lets us start creating a pattern
        RandomPattern pattern = new RandomPattern();
        pattern.setNumber(START_LENGHT);
        pattern.setIndexs(new Integer[]{0,0,0,-1});
        List<String> patlist = new ArrayList<String>(START_LENGHT);
        for(int i = 0; i < START_LENGHT; i++)
        {
            patlist.add(i,generateRandomList());
        }
        pattern.setArr(patlist);

        dao.save(RAND_COLL, pattern);

        return pattern;
    }

    public static String getRandomString() throws DataNotSavedException, DBNotAvailableException, DataNotFoundException {
        logger.info("Generating Topic Id..");
        RandomPattern pattern = readPattern();
        if(pattern == null)
            pattern = initpattern();

        for(int j = pattern.getNumber()-1; j >=0 ; j--)
        {
            if (pattern.getIndexs()[j] >= PAT_LENGHT - 1)
            {
                pattern.getIndexs()[j] = 0;
                if(j == 0)
                {
                    //We have readched the end of or available patterns
                    //We should add another String and increase the size of the topic name
                    RandomPattern newPattern = new RandomPattern();
                    newPattern.setNumber(pattern.getNumber()+1);
                    List<String> patlist= new ArrayList<String>(newPattern.getNumber());
                    for(int np = 0; np < pattern.getNumber(); np++)
                    {
                        patlist.add(np,pattern.getArr().get(np));
                    }
                    patlist.add(pattern.getNumber(),generateRandomList());
                    Integer indexes[] = new Integer[newPattern.getNumber()];
                    for (int k = 0; k < indexes.length; k++) {
                        indexes[k] = 0;
                    }
                    newPattern.setIndexs(indexes);
                    newPattern.setArr(patlist);
                    dao.save(RAND_COLL, newPattern);
                    pattern = newPattern;
                }
            }
            else
            {
                pattern.getIndexs()[j]++;
                break;
            }
        }

        //DAOFactory.getRandomPatternDAO().save(pattern);
        String rand = "";
        for (int i=0; i<pattern.getNumber();i++)
        {
            rand+=pattern.getArr().get(i).charAt(pattern.getIndexs()[i]);
        }

        logger.info("Topic ID generated using TopicNameGenerator Code:" + rand);
        return rand;
    }

    public static String generateRandomList() {
        StringBuffer ret = new StringBuffer();
        int positions[] = rand.nextPermutation(allowedChars.length, PAT_LENGHT);
        for (int i = 0; i < positions.length; i++) {
            int position = positions[i];
            ret.append(allowedChars[position]);
        }
        return ret.toString();
    }

    public static void test_generator(int start, int patlen) throws DataNotSavedException, DBNotAvailableException, DataNotFoundException
    {
        //Constants.START_LENGHT = start;
        //Constants.PAT_LENGHT = patlen;
        List<String>  pat = readPattern().getArr();
        for (int i = 0; i < pat.size(); i++) {
            String strings = pat.get(i);
            System.out.println("" + new String(strings));
        }
        for (int i = 0; i< 100000; i++)
        {
            getRandomString();
        }
    }

    private static RandomPattern readPattern() {
        try
        {
            return null;//DAOFactory.getRandomPatternDAO().find("1");
        } catch(Exception e) { //when exception return null
            return null;
        }

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            test_generator(2,3);
        } catch (DataNotSavedException e) {
            e.printStackTrace();
        } catch (DBNotAvailableException e) {
            e.printStackTrace();
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
