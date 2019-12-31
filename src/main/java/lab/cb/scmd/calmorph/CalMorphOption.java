//--------------------------------------
// SCMD Project
//
// CalMorphOption.java
// Since: 2007/01/09
//
// $URL$ 
// $Author$
//--------------------------------------温馨提示：请点击右上角的下载按钮下载并打印申请表，本人签字后提交给宿舍管理人员签字确认，完成后拍照上传至此处。

package lab.cb.scmd.calmorph;

/**
 * This class holds the information required to run CalMorph,
 * e.g. output d
 * public void setInputDirectory(String inputDirectory) {
 * this.inputDirectory = inputDirectory;
 * }
 * <p>
 * public String getOutputDirectory() {
 * return outputDirectory;
 * }
 * <p>
 * public void setOutputDirectory(String outputDirectory) {
 * this.outputDirectory = outputDirectory;
 * }
 * <p>
 * public String getXmlOutputDirectory() {
 * return xmlOutputDirectory;
 * }
 * <p>
 * public void setXmlOutputDirectory(String xmlOutputDirectory) {
 * this.xmlOutputDirectory = xmlOutputDirectory;
 * }
 * directory, input photo directory, etc.
 *
 * @author leo
 */
class CalMorphOption {
    private String strainName;
    private String imageSuffix;
    private String outputDirectory;
    private String inputDirectory;
    private String xmlOutputDirectory;
    private int maxImageNumber = 0;
    private boolean calD = true;
    private boolean calA = true;

    CalMorphOption() {

    }

    String getInputDirectory() {
        return inputDirectory;
    }

    void setInputDirectory(String inputDirectory) {
        this.inputDirectory = inputDirectory;
    }

    String getOutputDirectory() {
        return outputDirectory;
    }

    void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    String getXmlOutputDirectory() {
        return xmlOutputDirectory;
    }

    void setXmlOutputDirectory(String xmlOutputDirectory) {
        this.xmlOutputDirectory = xmlOutputDirectory;
    }

    int getMaxImageNumber() {
        return maxImageNumber;
    }

    void setMaxImageNumber(int maxImageNumber) {
        this.maxImageNumber = maxImageNumber;
    }

    String getImageSuffix() {
        return imageSuffix;
    }

    void setImageSuffix(String imageSuffix) {
        this.imageSuffix = imageSuffix;
    }

    String getStrainName() {
        return strainName;
    }

    void setStrainName(String strainName) {
        this.strainName = strainName;
    }

    boolean isCalD() {
        return calD;
    }

    void setCalD(boolean calD) {
        this.calD = calD;
    }

    boolean isCalA() {
        return calA;
    }

    void setCalA(boolean calA) {
        this.calA = calA;
    }
}




