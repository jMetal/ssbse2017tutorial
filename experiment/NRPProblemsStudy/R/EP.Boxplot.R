postscript("EP.Boxplot.eps", horizontal=FALSE, onefile=FALSE, height=8, width=12, pointsize=10)
resultDirectory<-"../data"
qIndicator <- function(indicator, problem)
{
fileNSGAII<-paste(resultDirectory, "NSGAII", sep="/")
fileNSGAII<-paste(fileNSGAII, problem, sep="/")
fileNSGAII<-paste(fileNSGAII, indicator, sep="/")
NSGAII<-scan(fileNSGAII)

fileSPEA2<-paste(resultDirectory, "SPEA2", sep="/")
fileSPEA2<-paste(fileSPEA2, problem, sep="/")
fileSPEA2<-paste(fileSPEA2, indicator, sep="/")
SPEA2<-scan(fileSPEA2)

fileMOCell<-paste(resultDirectory, "MOCell", sep="/")
fileMOCell<-paste(fileMOCell, problem, sep="/")
fileMOCell<-paste(fileMOCell, indicator, sep="/")
MOCell<-scan(fileMOCell)

fileMOCHC<-paste(resultDirectory, "MOCHC", sep="/")
fileMOCHC<-paste(fileMOCHC, problem, sep="/")
fileMOCHC<-paste(fileMOCHC, indicator, sep="/")
MOCHC<-scan(fileMOCHC)

algs<-c("NSGAII","SPEA2","MOCell","MOCHC")
boxplot(NSGAII,SPEA2,MOCell,MOCHC,names=algs, notch = TRUE)
titulo <-paste(indicator, problem, sep=":")
title(main=titulo)
}
par(mfrow=c(2,2))
indicator<-"EP"
qIndicator(indicator, "NRP1")
qIndicator(indicator, "NRP2")
qIndicator(indicator, "NRP3")
qIndicator(indicator, "NRP4")
