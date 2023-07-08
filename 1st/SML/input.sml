fun parse file =
    let
				(* A function to read an integer from specified input. *)
        fun readInt input =
	    	  Option.valOf (TextIO.scanStream (Int.scan StringCvt.DEC) input)

				(* Open input file. *)
    		val inStream = TextIO.openIn file

        (* Read two integers(nodes and edges) and consume newline. *)
				val n = readInt inStream
        val m = readInt inStream
				val _ = TextIO.inputLine inStream

        (* A function to read N integers from the open file. *)
				fun readInts (0, acc) = rev acc
	  			| readInts (i, acc) = readInts ((i - 1), (readInt inStream :: acc))
    in
        let
            fun listOfTriads nil = nil
              | listOfTriads [a] = nil
              | listOfTriads [a, b] = nil
              | listOfTriads (a::b::c::rest)  = (a, b, c) :: listOfTriads (rest)
        in
            (n, listOfTriads (readInts (2 * m, [])))
        end
    end
