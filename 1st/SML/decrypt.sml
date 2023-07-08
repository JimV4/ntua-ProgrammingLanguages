open Math;
(* Function to decrypt input for a specific N. Takes as
input a list of characters and N and returns decrypted text for this N *)
fun decryptN (nil, N) = nil
  | decryptN (a::input, N) =
    let
      (* char a to int ascii *)
      val inta = Char.ord(a)
    in
      (* Letter capital *)
      if (65 <= inta andalso inta <= 90) then
        if (inta + N > 90) then
          let
            val newa = 65 + N - (90 - inta) - 1
          in
            chr(newa)::(decryptN(input, N))
          end
        else
          let
            val newa = inta + N
          in
            chr(newa)::(decryptN(input, N))
            end

      (* Letter small *)
      else if (97 <= inta andalso inta <= 122) then
        if (inta + N > 122) then
          let
            val newa = 97 + N - (122 - inta) - 1
          in
            chr(newa)::(decryptN (input, N))
          end
        else
          let
            val newa = inta + N
          in
            chr(newa)::(decryptN (input, N))
          end

      (* Other characters stay as they are *)
      else chr(inta)::(decryptN (input, N))
    end

(* Returns the number of occurences of letter(cap or small) in list *)
fun Count (list, letter) =
  let
    fun loop [] count = count
      | loop (s::list) count =
        if Char.ord(s) = Char.ord(letter) orelse abs(Char.ord(s) - Char.ord(letter)) = 32
          then loop list (count + 1)
          else loop list count
  in
    loop list 0
  end

(*Returns the number of occurences of each letter in a list *)
fun numOfOccur ([], []) = nil
  | numOfOccur (list, []) = nil
  | numOfOccur ([], a::rest) = nil
  |	numOfOccur (list, a::rest) = (Count (list, a)) :: (numOfOccur (list, rest))

(* Takes as input the list of number of occurences of each letter
in the decrypted text and the frequency of letter in english text
and gives as result the calculated entropy for fixed N *)
fun entropy (list1, list2 : real list) =
  let
    fun helpEntr (_, []) = []
      | helpEntr ([], _) = []
      | helpEntr (a::rest1, b::rest2 : real list) =
        let
          fun Hc (x, y : real) = real((~1) * x) * log10(y)
        in
          Hc (a, b) :: helpEntr (rest1, rest2)
        end
  in
    let
      val newList = helpEntr(list1, list2)
    in
      foldr (op+) 0.0 newList
    end
  end

(* input taken as a list of characters
code link :
https://stackoverflow.com/questions/37015891/read-file-character-by-character-in-smlnj *)
fun parse file =
  let
    fun next_String input = (TextIO.inputAll input)
      val stream = TextIO.openIn file
      val a = next_String stream
  in
    explode(a)
  end

(*print a list of characters as text*)
fun printResult [] = print ("\n")
  | printResult (a::rest) =
    if a = #"\n" then (print ("\n") ; printResult (rest)) else
      (print (Char.toString(a)) ; printResult (rest))


(* assume that input text is in a list called input *)
fun mainFoo input =
  let
    val letters = [#"A", #"B", #"C", #"D", #"E", #"F",
                   #"G", #"H", #"I", #"J", #"K", #"L",
                   #"M", #"N", #"O", #"P", #"Q", #"R",
                   #"S", #"T", #"U", #"V", #"W", #"X",
                   #"Y", #"Z"]

    val f = [0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015,
             0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 0.02406, 0.06749,
             0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758,
             0.00978, 0.02360, 0.00150, 0.01974, 0.00074]

    (* list of entropies for every N whereas 0 <= N <= 25 *)
    fun entropiesList 26 = []
      | entropiesList N : real list =
        let
          val decrText = decryptN (input, N)
          val fN = numOfOccur (decrText, letters)
          val H = entropy (fN, f)
        in
          H :: entropiesList (N + 1)
        end

    (* find the N for which we have least entropy *)
    fun leastEntropy [] = 0
      | leastEntropy [a] = 0
      | leastEntropy (a::rest : real list) =
        let
          fun Pos (N, count, save, []) = count
            | Pos (N, count, save, a::rest : real list) =
              if N < a then  Pos (N, count, save + 1, rest)
                       else  Pos (a, save + 1, save + 1, rest)
        in
          Pos(a, 0, 0, rest)
        end

    (*position of least entropy(find proper N) *)
    val pos = leastEntropy (entropiesList 0)

  in
    let val output = decryptN (input, pos) in printResult output end
  end

fun decrypt fileName = let val input = parse fileName in mainFoo input end

(*val start = Timer.startRealTimer ();
decrypt "1.txt";
Time.toMilliseconds(Timer.checkRealTimer (start))*)
