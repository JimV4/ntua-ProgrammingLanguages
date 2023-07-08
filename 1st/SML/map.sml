fun MergeSort nil = nil
  | MergeSort [((u, v), c)] = [((u, v), c)]
  | MergeSort theList =
      let
          fun halve nil = (nil, nil)
            | halve [((u, v), c)] = ([((u, v), c)], nil)
            | halve ( ((u1, v1), c1) :: ((u2, v2), c2) :: rest) =
                let
                    val (x, y) = halve rest
                in
                    (((u1, v1), c1) :: x, ((u2, v2), c2) :: y)
                end

          fun merge (nil, ys) = ys
            | merge (xs, nil) = xs
            | merge (((u1, v1), c1) :: xs, ((u2, v2), c2) :: ys) =
                if c1 < c2 then ((u1, v1), c1) :: merge(xs, ((u2, v2), c2)::ys)
                           else ((u2, v2), c2) :: merge(ys, ((u1, v1), c1)::xs)
          val (x, y) = halve theList
      in
          merge (MergeSort x, MergeSort y)
      end

(* initialize parents. Every node belongs
  to it's own set; For example node 1 belongs to set 1 etc *)
fun parent n =
    let
        fun help (count, n) = if count = n + 1 then nil else count :: help (count + 1, n)
    in
        help(1, n)
    end

(* returns a new list of parents where
parent of u is parent of v *)
fun unionSet (u, v, nil) = nil
  | unionSet (u, v, a::rest) =
    let
      fun help (count, u, v, nil) = nil
        | help (count, u, v, a::rest) =
          if count = u then v::rest else a::help (count + 1, u, v, rest)
    in
        if u < v then help (1, u, v, a::rest) else help (1, v, u, a::rest)
    end

(* returns the set that u belongs to *)
fun findSet (u, nil) = u
  | findSet (u, a::parents) =
  let
      (* find the uth element of a list *)
      fun findPos (u, nil) = 0
        | findPos (u, a::rest) = if u = 1 then a else findPos (u - 1, rest)
  in
      let val parent_u = findPos (u, a::parents) in
          if u = parent_u then u else findSet(parent_u, a::parents)
      end
  end

(* return the MST *)
fun kruskal (nil, _) = nil
  | kruskal (_, nil) = nil
  | kruskal ( ((u, v), c) :: rest, parents) =
      let
          (* sort edges list on increasing cost *)
          (*val sortCost = MergeSort ( ((u, v), c) :: rest )*)
          (*val help = #1 (hd sortCost)*)
          (*val u1 = #1 help
          val v1 = #2 help*)
          val uRepresent = findSet (u, parents)
          val vRepresent = findSet (v, parents)
      in
          if uRepresent <> vRepresent then
              let
                  val newParents = unionSet (uRepresent, vRepresent, parents)
              in
                  (*(#2 (hd sortCost))*)c :: kruskal (rest, newParents)
              end
          else kruskal (rest, parents)
      end

fun parse file =
    let
        (* A function to read an integer from specified input. *)
        fun readInt input =
            Option.valOf (TextIO.scanStream (Int.scan StringCvt.DEC) input)

        (* Open input file. *)
        val inStream = TextIO.openIn file

        (* Read an integer and consume newline. *)
        val n = readInt inStream
        val _ = TextIO.inputLine inStream

        fun createGraph

fun mainFoo (nil, n) = 0
  | mainFoo (inputGraph, n) =
      let
          (* initial parent list where each node is parent of itself *)
          val parents = parent n
          val sortCost = MergeSort inputGraph
          val mstree = kruskal (sortCost, parents)
          fun maxCost [] = 0
            | maxCost [x] = x
            | maxCost (x::rest) =
                let
                    val y = maxCost rest
                in
                    if x > y then x else y
                end
      in
          maxCost mstree
      end
