# PigLatinTask

##Design
It is implemented with functional programming principles. Performance are 
not taken in consideration for the single word, but it is easy to 
parallelize splitting the single word encoding to different core/machine. 

##Assumptions
It is assumed the text is in latin alphabet with english punctuation. 
It is assumed the capitalization in case of consonant is happening on 
the same cardinality of the original word and encoded word from left to
right (HellO -> ElloHay) so ay is never capitalized. 
Punctuation is applied from right to left on the capitalized world.

##Usage
``` 
TextEncoder encoder = new TextEncoder();
String encodedText = encoder.encodeText("Hello world");
assert("Ellohay orldway", encodedText)
```

## Installation
```mvn clean install```

## Run Test
```mvn test```

