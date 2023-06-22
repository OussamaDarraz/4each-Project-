package business.anemo.repository;


import business.anemo.domain.AnemometreValue;
import business.anemo.domain.ConfigurationAnemo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.LoadProperties;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.sql.Timestamp;
public class AnemoRepository {
    private final static LoadProperties props = new LoadProperties("queries.properties");
    private final static Logger logger = LoggerFactory.getLogger(AnemoRepository.class.getName());
    private final Connection connection;

    public AnemoRepository(Connection connection) {
        this.connection = connection;
    }

    public List<AnemometreValue> findByObjectLabel(String label) {
        List<AnemometreValue> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("selectByAnemo"));
            preparedStatement.setString(1, label);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                AnemometreValue value = new AnemometreValue(
                        rs.getInt("value_id"),
                        rs.getString("position_anemo"),
                        rs.getString("direction_anemo"),
                        rs.getFloat("speed_anemo"),
                        rs.getString("timedate_anemo"),
                        rs.getString("obj_label")
                );
                result.add(value);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<String> findAllObjectLabel() {
        List<String> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(props.getProp("selectAllAnemo"));
            while (rs.next()) {
                result.add(rs.getString("obj_label"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public Optional<ConfigurationAnemo>  findAnemoConfigByLabel(String label) {
        try (PreparedStatement statement = connection.prepareStatement(props.getProp("configAnemoRead"));){
            statement.setString(1, label);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return Optional.of(
                        new ConfigurationAnemo(
                                rs.getInt("id_config"),
                                rs.getString("direction_anemo"),
                                rs.getFloat("level_speed"),
                                label,
                                rs.getBoolean("status_anemo")
                        )
                );
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOrUpdateAnemoConfig(ConfigurationAnemo configurationAnemo) {
        try {
            String label = configurationAnemo.getLabel();
            Optional<ConfigurationAnemo> optionalconfig = findAnemoConfigByLabel(label);
            if(optionalconfig.isPresent()) {
                ConfigurationAnemo configFound = optionalconfig.get();
                logger.info("The config found {}", configFound);
                PreparedStatement statement = connection.prepareStatement(props.getProp("configAnemoUpdate"));
                statement.setFloat(1, configurationAnemo.getSpeed() < 0 ? configFound.getSpeed() : configurationAnemo.getSpeed());
                statement.setString(2, getOrDefault(configurationAnemo.getDirection(), configFound.getDirection()));
                statement.setBoolean(3, getOrDefault(configurationAnemo.isStatus(), configFound.isStatus()));
                statement.setString(4, label);
                statement.executeUpdate();

                logger.info("The {} anemo config has been updated", label);
            } else {
                PreparedStatement statement = connection.prepareStatement(props.getProp("configAnemoCreate"));
                statement.setFloat(1, configurationAnemo.getSpeed());
                statement.setString(2, configurationAnemo.getDirection());
                statement.setBoolean(3, configurationAnemo.isStatus());
                statement.setString(4, label);

                statement.execute();

                logger.info("The {} anemo config has been created", label);
            }

            //Please GC close statements


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private <T> T getOrDefault(T t, T replaced) {
        return Objects.nonNull(t) ? t: replaced;
    }

    public int differenceBetweenSpeed(String label)  {

        try {

        PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("differenceAnemoSpeedConfig"));
        preparedStatement.setString(1, label);
        preparedStatement.setString(2, label);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();

            return rs.getInt("dif");

    } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }
    public int differenceBetweenSpeedGust(String label)  {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("lastValue"));
            preparedStatement.setString(1, label);
            ResultSet rs = preparedStatement.executeQuery();
             rs.next();
            int i = rs.getInt("lastValue");
            PreparedStatement preparedStatement1 = connection.prepareStatement(props.getProp("averageValue"));
            preparedStatement1.setString(1, label);
            ResultSet rs1 = preparedStatement1.executeQuery();
            rs1.next();
            int j =rs1.getInt(1);
                System.out.println( "RESULTAT -------------lastValue="+i+"- Avg="+j+"--------------------------"+(i-j));
            return i-j;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }
    public int numberAnemoConfigured ()  {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("countAnemoConfigured"));
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            return rs.getInt("numbC");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }
    public int numberAnemo ()  {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("countAnemo"));
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            return rs.getInt("numbAll");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }
    public List<ConfigurationAnemo> testSelectAllConfiguration() {
        List<ConfigurationAnemo> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(props.getProp("selectConfigurationOfAnemo"));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ConfigurationAnemo value = new ConfigurationAnemo(
                        rs.getInt("id_config"),
                        rs.getString("direction_anemo"),
                        rs.getFloat("level_speed"),
                        rs.getString("obj_label"),
                        rs.getBoolean("status_anemo")

                );
                result.add(value);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void Insertvalues(){

        try{
            TypeReference< List<AnemometreValue> > tRef = new TypeReference< List<AnemometreValue> >() {};
            List<AnemometreValue> anemometreValues = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
                anemometreValues = mapper.readValue(new File("server\\src\\main\\resources\\anemoValues.json"),tRef);


                for(AnemometreValue anemometreValue:anemometreValues){
                    PreparedStatement statement = connection.prepareStatement(props.getProp("insertValue"));
                statement.setInt(1,anemometreValue.getId());
                statement.setString(2,anemometreValue.getPosition());
                statement.setString( 3,anemometreValue.getDirection());
                statement.setFloat(4,anemometreValue.getSpeed());
                statement.setTimestamp(5,Timestamp.valueOf(anemometreValue.getTimedate()));
                statement.setString(6,anemometreValue.getLabel());
                statement.executeUpdate();

            } } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


    } }
