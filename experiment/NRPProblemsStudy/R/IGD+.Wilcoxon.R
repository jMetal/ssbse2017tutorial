write("", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex",append=FALSE)
resultDirectory<-"/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/data"
latexHeader <- function() {
  write("\\documentclass{article}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\title{StandardStudy}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\usepackage{amssymb}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\author{A.J.Nebro}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\begin{document}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\maketitle", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\section{Tables}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
}

latexTableHeader <- function(problem, tabularString, latexTableFirstLine) {
  write("\\begin{table}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\caption{", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write(problem, "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write(".IGD+.}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)

  write("\\label{Table:", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write(problem, "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write(".IGD+.}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)

  write("\\centering", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\begin{scriptsize}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\begin{tabular}{", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write(tabularString, "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write(latexTableFirstLine, "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\hline ", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
}

printTableLine <- function(indicator, algorithm1, algorithm2, i, j, problem) { 
  file1<-paste(resultDirectory, algorithm1, sep="/")
  file1<-paste(file1, problem, sep="/")
  file1<-paste(file1, indicator, sep="/")
  data1<-scan(file1)
  file2<-paste(resultDirectory, algorithm2, sep="/")
  file2<-paste(file2, problem, sep="/")
  file2<-paste(file2, indicator, sep="/")
  data2<-scan(file2)
  if (i == j) {
    write("-- ", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  }
  else if (i < j) {
    if (is.finite(wilcox.test(data1, data2)$p.value) & wilcox.test(data1, data2)$p.value <= 0.05) {
      if (median(data1) <= median(data2)) {
        write("$\\blacktriangle$", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
      }
      else {
        write("$\\triangledown$", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE) 
      }
    }
    else {
      write("--", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE) 
    }
  }
  else {
    write(" ", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  }
}

latexTableTail <- function() { 
  write("\\hline", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\end{tabular}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\end{scriptsize}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\end{table}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
}

latexTail <- function() { 
  write("\\end{document}", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
}

### START OF SCRIPT 
# Constants
problemList <-c("NRP1", "NRP2", "NRP3", "NRP4") 
algorithmList <-c("NSGAII", "SPEA2", "MOCell", "MOCHC") 
tabularString <-c("lccc") 
latexTableFirstLine <-c("\\hline  & SPEA2 & MOCell & MOCHC\\\\ ") 
indicator<-"IGD+"

 # Step 1.  Writes the latex header
latexHeader()
tabularString <-c("| l | p{0.15cm }p{0.15cm }p{0.15cm }p{0.15cm } | p{0.15cm }p{0.15cm }p{0.15cm }p{0.15cm } | p{0.15cm }p{0.15cm }p{0.15cm }p{0.15cm } | ") 

latexTableFirstLine <-c("\\hline \\multicolumn{1}{|c|}{} & \\multicolumn{4}{c|}{SPEA2} & \\multicolumn{4}{c|}{MOCell} & \\multicolumn{4}{c|}{MOCHC} \\\\") 

# Step 3. Problem loop 
latexTableHeader("NRP1 NRP2 NRP3 NRP4 ", tabularString, latexTableFirstLine)

indx = 0
for (i in algorithmList) {
  if (i != "MOCHC") {
    write(i , "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
    write(" & ", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)

    jndx = 0
    for (j in algorithmList) {
      for (problem in problemList) {
        if (jndx != 0) {
          if (i != j) {
            printTableLine(indicator, i, j, indx, jndx, problem)
          }
          else {
            write("  ", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
          } 
          if (problem == "NRP4") {
            if (j == "MOCHC") {
              write(" \\\\ ", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
            } 
            else {
              write(" & ", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
            }
          }
     else {
    write("&", "/Users/ajnebro/Softw/ssbse2017tutorial/experiment/NRPProblemsStudy/R/IGD+.Wilcoxon.tex", append=TRUE)
     }
        }
      }
      jndx = jndx + 1
    }
    indx = indx + 1
  }
} # for algorithm

  latexTableTail()

#Step 3. Writes the end of latex file 
latexTail()

