/**
  * Cristiano: Rules for implicit arguments
  *
  * 1 - Only the last argument list, including the only list for a single-list method, can have implicit arguments.
  * 2 - The implicit keyword must appear first and only once in the argument list. The list can’t have “nonimplicit”
  *     arguments followed by implicit arguments.
  *  3 - All the arguments are implicit when the list starts with the implicit keyword.
  *
  */