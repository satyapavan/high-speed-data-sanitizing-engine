# log-sanitizing-engine

# Agenda 
To enable a log sanitizing system that replaces sensitive data with a masked information. There are lot of tools existing for this, but they all depend on a pattern/structured data like IP/PWD/Telephone number and all. This engine, aims to provide masking for text's which adher to no patterns, like our greedytext. 

## Good Reads
- https://lwn.net/Articles/449460/
- https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string-search_algorithm
- https://docs.spring.io/spring-kafka/reference/html/#even-quicker-with-spring-boot
- https://stackoverflow.com/a/58199878 (Good one about the performance benchmarks)


TODO
performance of multi-pattern string match in java vs python

https://stackoverflow.com/questions/19829892/java-regular-expressions-performance-and-alternative

https://stackoverflow.com/questions/1326682/java-replacing-multiple-different-substring-in-a-string-at-once-or-in-the-most

https://github.com/almondtools/stringbench

https://github.com/intel/hyperscan

https://www.hyperscan.io/

Good - https://stackoverflow.com/questions/8845245/high-performance-mass-short-string-search-in-python

https://en.wikipedia.org/wiki/Aho%E2%80%93Corasick_algorithm

https://en.wikipedia.org/wiki/Commentz-Walter_algorithm

http://se.ethz.ch/~meyer/publications/string/string_matching.pdf

https://wiomoc.de/aho-corasick-viz/

https://github.com/cloudflare/ahocorasick

Good - https://stackoverflow.com/questions/1250399/algorithm-for-linear-pattern-matching

https://en.wikipedia.org/wiki/Trie

https://pypi.org/project/pytst/

https://stackoverflow.com/questions/49950747/why-is-string-comparison-so-fast-in-python

https://stackoverflow.com/questions/42742810/speed-up-millions-of-regex-replacements-in-python-3

https://stackoverflow.com/questions/6690739/high-performance-fuzzy-string-comparison-in-python-use-levenshtein-or-difflib

https://bergvca.github.io/2017/10/14/super-fast-string-matching.html

https://wiki.python.org/moin/PythonSpeed/PerformanceTips

https://www.quora.com/What-are-the-best-open-source-high-performance-string-matching-libraries

https://stackoverflow.com/questions/18340097/what-is-the-fastest-substring-search-method-in-java

https://stackoverflow.com/questions/7505160/high-performance-simple-java-regular-expressions

https://stackoverflow.com/questions/11663648/high-speed-string-matching-algorithms

https://johannburkard.de/software/stringsearch/

https://johannburkard.de/software/stringsearch/

https://www.theserverside.com/discussions/thread/61661.html
