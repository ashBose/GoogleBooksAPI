package com.books;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.apache.log4j.Logger;

import java.util.*;

public class ProcessArg {

    final static Logger logger = Logger.getLogger(ProcessArg.class);
    private String[] args = null;
    private Options options = new Options();
    Map<String, String> argMap = null;
    Set<String> filterSort = new HashSet<String>(Arrays.asList("avg_rating",
            "rating_count",
            "page_count",
            "published_date",
            "retail_price"));

    public ProcessArg(String[] args) {
        this.args = args;
        this.argMap = new HashMap<String, String>();
        options.addOption("h", "help",
                false, "show help.");
        options.addOption("s", "sort",
                true, "parameter used to sort use the " +
                        "following " +
                        "avg_rating, rating_count, page_count, published_date, " +
                        "retail_price");
        options.addOption("q", "query",
                true, "query to search for");
        options.addOption("b", "book",
                true, "book name to search for, " +
                        "insert books seperated by ','");
    }

    public Map<String, String> parse() throws ParseException{
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        String arg;
        try {
            cmd = parser.parse(options, args);
            if(args.length == 0) {
                throw new ParseException("No argument present");
            }
            if (cmd.hasOption("h")) {
                help();
                System.exit(0);
            }
            if (cmd.hasOption("q")) {
                argMap.put("query",cmd.getOptionValue("q"));
            } else {
                throw new ParseException("query parameter not present");
            }
            if (cmd.hasOption("s")) {
                arg = cmd.getOptionValue("s");
                if(!filterSort.contains(arg))
                    throw new ParseException("sort parameter not present");
                argMap.put("sort",arg);
            } else {
                throw new ParseException("sort parameter not present");
            }
            if (cmd.hasOption("b")) {
                argMap.put("book",cmd.getOptionValue("b"));
            } else {
                throw new ParseException("book parameter not present");
            }
        } catch (ParseException e) {
            help();
            throw new ParseException(e.getMessage());
        }
        return argMap;
    }

    private void help() {
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("AppEntryMain", options);
    }
}
