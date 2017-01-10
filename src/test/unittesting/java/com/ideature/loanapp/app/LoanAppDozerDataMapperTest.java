package com.ideature.loanapp.app;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ideature.loanapp.domain.Borrower;
import com.ideature.loanapp.domain.FundingRequest;
import com.ideature.loanapp.domain.Loan;
import com.ideature.loanapp.dto.FundingRequestDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/loanapp-datamapper-config.xml"})
public class LoanAppDozerDataMapperTest {
	
	private static final Log log = LogFactory.getLog(LoanAppDozerDataMapperTest.class);

    @Autowired
    private DozerBeanMapper mapper;

	@Test
	public void runTestDtoToDomain() {
		log.debug("Dto-->Domain Mapper Test");
		
		// Create DTO object
		FundingRequestDTO dto = new FundingRequestDTO();
		
		// loan details
		long loanId = 12345;
		String productGroup = "FIXED";
		BigDecimal loanAmount = new BigDecimal("500000");
		BigDecimal purchasePrice = new BigDecimal("600000");
		String propertyAddress = "123 MAIN STREET";

		dto.setLoanId(loanId);
		dto.setProductGroup(productGroup);
//		dto.setProductName("15-YR NON AGENCY FIXED");
		dto.setLoanAmount(loanAmount);
		dto.setPurchasePrice(purchasePrice);
		dto.setPropertyAddress(propertyAddress);
		
		// borrower details
		String firstName = "TEST";
		String lastName = "BORROWER";
		String phoneNumber = "123-456-7890";
		String emailAddress = "test.borrower@tb.com";
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setPhoneNumber(phoneNumber);
		dto.setEmailAddress(emailAddress);
		
		// funding details
		String fundType = "FULL DISBURSE";
		BigDecimal fundingAmount = new BigDecimal("500000");
		int termInMonths = 360;
		
		dto.setFundType(fundType);
		dto.setFundingAmount(fundingAmount);
		dto.setTermInMonths(termInMonths);
		
		log.debug("dto:" + dto.toString());

		Loan loan = (Loan) mapper.map(dto, Loan.class);
		log.debug("\nloan:" + ToStringBuilder.reflectionToString(loan, ToStringStyle.MULTI_LINE_STYLE));

		Borrower borrower = (Borrower) mapper.map(dto, Borrower.class);
		log.debug("\nloan:" + ToStringBuilder.reflectionToString(borrower, ToStringStyle.MULTI_LINE_STYLE));

		FundingRequest fundingRequest = (FundingRequest) mapper.map(dto, FundingRequest.class);
		log.debug("\nloan:" + ToStringBuilder.reflectionToString(fundingRequest, ToStringStyle.MULTI_LINE_STYLE));
		
	}

}
