// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template

/**
 * Built-in method bodies. An empty JunkCodeConfig.methodTemplates selects from all entries.
 * Names are stable configuration identifiers; do not persist ordinal values.
 */
enum class MethodTemplate {
    // Original templates. LEGACY_THROW_EXCEPTION throws when invoked.
    LEGACY_TIME_BRANCH,
    LEGACY_ACCUMULATE,
    LEGACY_THROW_EXCEPTION,
    LEGACY_CURRENT_DATE,
    LEGACY_PRINT,

    // Numeric
    /** Int sum. */
    INT_SUM,
    /** Int product. */
    INT_PRODUCT,
    /** Int gcd. */
    INT_GCD,
    /** Int lcm. */
    INT_LCM,
    /** Int fibonacci. */
    INT_FIBONACCI,
    /** Int prime check. */
    INT_PRIME_CHECK,
    /** Int prime count. */
    INT_PRIME_COUNT,
    /** Int digit sum. */
    INT_DIGIT_SUM,
    /** Int digit reverse. */
    INT_DIGIT_REVERSE,
    /** Int digit count. */
    INT_DIGIT_COUNT,
    /** Int power. */
    INT_POWER,
    /** Int sqrt. */
    INT_SQRT,
    /** Int divisor count. */
    INT_DIVISOR_COUNT,
    /** Int perfect check. */
    INT_PERFECT_CHECK,
    /** Int triangular. */
    INT_TRIANGULAR,
    /** Int binomial. */
    INT_BINOMIAL,
    /** Int collatz steps. */
    INT_COLLATZ_STEPS,
    /** Int digital root. */
    INT_DIGITAL_ROOT,
    /** Int armstrong check. */
    INT_ARMSTRONG_CHECK,
    /** Double harmonic sum. */
    DOUBLE_HARMONIC_SUM,
    /** Double newton sqrt. */
    DOUBLE_NEWTON_SQRT,
    /** Double polynomial. */
    DOUBLE_POLYNOMIAL,
    /** Double weighted mean. */
    DOUBLE_WEIGHTED_MEAN,
    /** Double compound interest. */
    DOUBLE_COMPOUND_INTEREST,
    /** Int base conversion. */
    INT_BASE_CONVERSION,

    // Bits
    /** Bit popcount. */
    BIT_POPCOUNT,
    /** Bit parity. */
    BIT_PARITY,
    /** Bit reverse. */
    BIT_REVERSE,
    /** Bit rotate left. */
    BIT_ROTATE_LEFT,
    /** Bit rotate right. */
    BIT_ROTATE_RIGHT,
    /** Bit power of two. */
    BIT_POWER_OF_TWO,
    /** Bit next power of two. */
    BIT_NEXT_POWER_OF_TWO,
    /** Bit gray encode. */
    BIT_GRAY_ENCODE,
    /** Bit gray decode. */
    BIT_GRAY_DECODE,
    /** Bit hamming distance. */
    BIT_HAMMING_DISTANCE,
    /** Bit pack bytes. */
    BIT_PACK_BYTES,
    /** Bit unpack bytes. */
    BIT_UNPACK_BYTES,
    /** Bit interleave. */
    BIT_INTERLEAVE,
    /** Bit trailing zero count. */
    BIT_TRAILING_ZERO_COUNT,
    /** Bit submask sum. */
    BIT_SUBMASK_SUM,

    // Strings
    /** String reverse. */
    STRING_REVERSE,
    /** String palindrome. */
    STRING_PALINDROME,
    /** String vowel count. */
    STRING_VOWEL_COUNT,
    /** String word count. */
    STRING_WORD_COUNT,
    /** String run length encode. */
    STRING_RUN_LENGTH_ENCODE,
    /** String run length decode. */
    STRING_RUN_LENGTH_DECODE,
    /** String remove adjacent duplicates. */
    STRING_REMOVE_ADJACENT_DUPLICATES,
    /** String rotate. */
    STRING_ROTATE,
    /** String anagram check. */
    STRING_ANAGRAM_CHECK,
    /** String common prefix. */
    STRING_COMMON_PREFIX,
    /** String title case. */
    STRING_TITLE_CASE,
    /** String camel to snake. */
    STRING_CAMEL_TO_SNAKE,
    /** String snake to camel. */
    STRING_SNAKE_TO_CAMEL,
    /** String collapse spaces. */
    STRING_COLLAPSE_SPACES,
    /** String count substring. */
    STRING_COUNT_SUBSTRING,
    /** String escape xml. */
    STRING_ESCAPE_XML,
    /** String caesar shift. */
    STRING_CAESAR_SHIFT,
    /** String hex encode. */
    STRING_HEX_ENCODE,
    /** String hex decode. */
    STRING_HEX_DECODE,
    /** String balanced parentheses. */
    STRING_BALANCED_PARENTHESES,
    /** String first unique char. */
    STRING_FIRST_UNIQUE_CHAR,
    /** String reverse word order. */
    STRING_REVERSE_WORD_ORDER,
    /** String pad left. */
    STRING_PAD_LEFT,
    /** String remove digits. */
    STRING_REMOVE_DIGITS,
    /** String rolling hash. */
    STRING_ROLLING_HASH,
    /** String edit distance. */
    STRING_EDIT_DISTANCE,
    /** String subsequence check. */
    STRING_SUBSEQUENCE_CHECK,
    /** String longest word. */
    STRING_LONGEST_WORD,
    /** String interleave. */
    STRING_INTERLEAVE,
    /** String bigram count. */
    STRING_BIGRAM_COUNT,

    // Arrays
    /** Array sum. */
    ARRAY_SUM,
    /** Array min max range. */
    ARRAY_MIN_MAX_RANGE,
    /** Array reverse. */
    ARRAY_REVERSE,
    /** Array rotate. */
    ARRAY_ROTATE,
    /** Array prefix sum. */
    ARRAY_PREFIX_SUM,
    /** Array differences. */
    ARRAY_DIFFERENCES,
    /** Array binary search. */
    ARRAY_BINARY_SEARCH,
    /** Array lower bound. */
    ARRAY_LOWER_BOUND,
    /** Array bubble sort. */
    ARRAY_BUBBLE_SORT,
    /** Array insertion sort. */
    ARRAY_INSERTION_SORT,
    /** Array selection sort. */
    ARRAY_SELECTION_SORT,
    /** Array counting sort. */
    ARRAY_COUNTING_SORT,
    /** Array merge sorted. */
    ARRAY_MERGE_SORTED,
    /** Array dedup sorted. */
    ARRAY_DEDUP_SORTED,
    /** Array partition even. */
    ARRAY_PARTITION_EVEN,
    /** Array move zeros. */
    ARRAY_MOVE_ZEROS,
    /** Array max subarray. */
    ARRAY_MAX_SUBARRAY,
    /** Array window sum. */
    ARRAY_WINDOW_SUM,
    /** Array two sum. */
    ARRAY_TWO_SUM,
    /** Array majority. */
    ARRAY_MAJORITY,
    /** Array missing number. */
    ARRAY_MISSING_NUMBER,
    /** Array product except self. */
    ARRAY_PRODUCT_EXCEPT_SELF,
    /** Array dot product. */
    ARRAY_DOT_PRODUCT,
    /** Array median. */
    ARRAY_MEDIAN,
    /** Array variance. */
    ARRAY_VARIANCE,
    /** Array longest increasing run. */
    ARRAY_LONGEST_INCREASING_RUN,
    /** Array local peak count. */
    ARRAY_LOCAL_PEAK_COUNT,
    /** Array inversion count. */
    ARRAY_INVERSION_COUNT,
    /** Array pairwise distance. */
    ARRAY_PAIRWISE_DISTANCE,
    /** Array convolution. */
    ARRAY_CONVOLUTION,

    // Lists
    /** List filter even. */
    LIST_FILTER_EVEN,
    /** List square values. */
    LIST_SQUARE_VALUES,
    /** List flatten. */
    LIST_FLATTEN,
    /** List zip sum. */
    LIST_ZIP_SUM,
    /** List chunk. */
    LIST_CHUNK,
    /** List interleave. */
    LIST_INTERLEAVE,
    /** List insert sorted. */
    LIST_INSERT_SORTED,
    /** List remove negative. */
    LIST_REMOVE_NEGATIVE,
    /** List partition. */
    LIST_PARTITION,
    /** List take while. */
    LIST_TAKE_WHILE,
    /** List drop while. */
    LIST_DROP_WHILE,
    /** List pairwise sum. */
    LIST_PAIRWISE_SUM,
    /** List cartesian product. */
    LIST_CARTESIAN_PRODUCT,
    /** List stack evaluate. */
    LIST_STACK_EVALUATE,
    /** List queue round robin. */
    LIST_QUEUE_ROUND_ROBIN,
    /** List josephus. */
    LIST_JOSEPHUS,
    /** List top k. */
    LIST_TOP_K,
    /** List sort by length. */
    LIST_SORT_BY_LENGTH,
    /** List sliding max. */
    LIST_SLIDING_MAX,
    /** List undo history. */
    LIST_UNDO_HISTORY,

    // Maps
    /** Map word frequency. */
    MAP_WORD_FREQUENCY,
    /** Map group by length. */
    MAP_GROUP_BY_LENGTH,
    /** Map invert. */
    MAP_INVERT,
    /** Map merge counts. */
    MAP_MERGE_COUNTS,
    /** Map filter values. */
    MAP_FILTER_VALUES,
    /** Map max entry. */
    MAP_MAX_ENTRY,
    /** Map index words. */
    MAP_INDEX_WORDS,
    /** Map first seen index. */
    MAP_FIRST_SEEN_INDEX,
    /** Map last seen index. */
    MAP_LAST_SEEN_INDEX,
    /** Map two sum indices. */
    MAP_TWO_SUM_INDICES,
    /** Map subarray sum count. */
    MAP_SUBARRAY_SUM_COUNT,
    /** Map isomorphic strings. */
    MAP_ISOMORPHIC_STRINGS,
    /** Map anagram groups. */
    MAP_ANAGRAM_GROUPS,
    /** Map sparse dot product. */
    MAP_SPARSE_DOT_PRODUCT,
    /** Map lru order. */
    MAP_LRU_ORDER,
    /** Map bucket histogram. */
    MAP_BUCKET_HISTOGRAM,
    /** Map flatten entries. */
    MAP_FLATTEN_ENTRIES,
    /** Map parse pairs. */
    MAP_PARSE_PAIRS,
    /** Map floor lookup. */
    MAP_FLOOR_LOOKUP,
    /** Map range keys. */
    MAP_RANGE_KEYS,

    // Sets
    /** Set union. */
    SET_UNION,
    /** Set intersection. */
    SET_INTERSECTION,
    /** Set difference. */
    SET_DIFFERENCE,
    /** Set symmetric difference. */
    SET_SYMMETRIC_DIFFERENCE,
    /** Set subset check. */
    SET_SUBSET_CHECK,
    /** Set disjoint check. */
    SET_DISJOINT_CHECK,
    /** Set ordered dedup. */
    SET_ORDERED_DEDUP,
    /** Set duplicate values. */
    SET_DUPLICATE_VALUES,
    /** Set longest consecutive. */
    SET_LONGEST_CONSECUTIVE,
    /** Set happy number. */
    SET_HAPPY_NUMBER,
    /** Set missing range. */
    SET_MISSING_RANGE,
    /** Set distinct window. */
    SET_DISTINCT_WINDOW,
    /** Set power set. */
    SET_POWER_SET,
    /** Set jaccard similarity. */
    SET_JACCARD_SIMILARITY,
    /** Set sieve primes. */
    SET_SIEVE_PRIMES,

    // Control
    /** Control clamp. */
    CONTROL_CLAMP,
    /** Control sign. */
    CONTROL_SIGN,
    /** Control grade. */
    CONTROL_GRADE,
    /** Control leap year. */
    CONTROL_LEAP_YEAR,
    /** Control days in month. */
    CONTROL_DAYS_IN_MONTH,
    /** Control traffic light. */
    CONTROL_TRAFFIC_LIGHT,
    /** Control vending machine. */
    CONTROL_VENDING_MACHINE,
    /** Control run transitions. */
    CONTROL_RUN_TRANSITIONS,
    /** Control debounce. */
    CONTROL_DEBOUNCE,
    /** Control token classify. */
    CONTROL_TOKEN_CLASSIFY,
    /** Control parse integer. */
    CONTROL_PARSE_INTEGER,
    /** Control checksum validate. */
    CONTROL_CHECKSUM_VALIDATE,
    /** Control interval overlap. */
    CONTROL_INTERVAL_OVERLAP,
    /** Control binary flags. */
    CONTROL_BINARY_FLAGS,
    /** Control fizz buzz. */
    CONTROL_FIZZ_BUZZ,
    /** Control coin change. */
    CONTROL_COIN_CHANGE,
    /** Control knapsack. */
    CONTROL_KNAPSACK,
    /** Control stair ways. */
    CONTROL_STAIR_WAYS,
    /** Control interval merge. */
    CONTROL_INTERVAL_MERGE,
    /** Control roman numeral. */
    CONTROL_ROMAN_NUMERAL,

    // Matrices
    /** Matrix transpose. */
    MATRIX_TRANSPOSE,
    /** Matrix multiply. */
    MATRIX_MULTIPLY,
    /** Matrix trace. */
    MATRIX_TRACE,
    /** Matrix determinant. */
    MATRIX_DETERMINANT,
    /** Matrix rotate clockwise. */
    MATRIX_ROTATE_CLOCKWISE,
    /** Matrix spiral. */
    MATRIX_SPIRAL,
    /** Matrix row sums. */
    MATRIX_ROW_SUMS,
    /** Matrix column max. */
    MATRIX_COLUMN_MAX,
    /** Matrix symmetry. */
    MATRIX_SYMMETRY,
    /** Matrix identity. */
    MATRIX_IDENTITY,
    /** Matrix prefix sum. */
    MATRIX_PREFIX_SUM,
    /** Matrix grid paths. */
    MATRIX_GRID_PATHS,
    /** Matrix min path cost. */
    MATRIX_MIN_PATH_COST,
    /** Matrix neighbor sum. */
    MATRIX_NEIGHBOR_SUM,
    /** Matrix checkerboard. */
    MATRIX_CHECKERBOARD,
    /** Matrix flood fill. */
    MATRIX_FLOOD_FILL,
    /** Matrix life step. */
    MATRIX_LIFE_STEP,
    /** Matrix toeplitz check. */
    MATRIX_TOEPLITZ_CHECK,
    /** Matrix saddle point. */
    MATRIX_SADDLE_POINT,
    /** Matrix zero rows columns. */
    MATRIX_ZERO_ROWS_COLUMNS,
}
