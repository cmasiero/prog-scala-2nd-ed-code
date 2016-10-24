/**
  * Cristiano:Implicit
  * Implicit Evidence
  * Sometimes, we just need to constrain the allowed types and not provide
  * additional processing capability.
  * Put another way, we need “evidence” that the proposed types satisfy
  * our requirements.
  *
  * es toMap : def toMap[ T, U](implicit ev: <:<[A, (T, U)]):
  *
  * A must be a sequence of pair
  */